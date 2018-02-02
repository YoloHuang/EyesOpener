package com.example.hzj.EyeOpener.presenter;

import android.util.Log;

import com.example.hzj.EyeOpener.base.Contract.DailyContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.DailyBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by hzj on 2017/12/20.
 * DailyFragment所关联的presenter
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    List<ItemListBean> itemListBeans;
    List<ItemListBean> moreItemListBeans;
    List<ItemListBean> firstItemListBeans = new ArrayList<>();
    String nextUrl;
    long nextDate;
    int toplistNum;

    private Disposable intervalDisposable;


    @Inject
    public DailyPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    /**
     * 获取每日数据
     */
    @Override
    public void getDailyData() {
        addSubscribe(mDataManager.getDailyBean()
                .compose(RxUtil.<DailyBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
                    @Override
                    public void onNext(DailyBean dailyBean) {
                        Log.d("hzj", "onNext: " + dailyBean.getIssueList().size());
                        nextUrl = dailyBean.getNextPageUrl();
                        String nextDateName = nextUrl.substring(nextUrl.indexOf("=") + 1, nextUrl.indexOf("&"));
                        nextDate = Long.decode(nextDateName);
                        for (ItemListBean itemListBean : dailyBean.getIssueList().get(0).getItemList()) {
                            if (itemListBean.getType().equals("video")) {
                                firstItemListBeans.add(itemListBean);
                            }
                        }
                        toplistNum = firstItemListBeans.size();
                        mView.showFirstContent(firstItemListBeans);
                        if (null != dailyBean.getIssueList().get(1)) {
                            itemListBeans = dailyBean.getIssueList().get(1).getItemList();
                        }
                        mView.showContent(itemListBeans);
                    }
                }));
    }

    /**
     * 获取往日数据
     */
    @Override
    public void getMoreData() {
        addSubscribe(mDataManager.getDailyBean(nextDate)
                .compose(RxUtil.<DailyBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
                    @Override
                    public void onNext(DailyBean dailyBean) {
                        nextUrl = dailyBean.getNextPageUrl();
                        String nextDateName = nextUrl.substring(nextUrl.indexOf("=") + 1, nextUrl.indexOf("&"));
                        nextDate = Long.decode(nextDateName);
                        moreItemListBeans = dailyBean.getIssueList().get(0).getItemList();
                        if (null != dailyBean.getIssueList().get(1)) {
                            moreItemListBeans.addAll(dailyBean.getIssueList().get(1).getItemList());
                        }
                        mView.showContent(moreItemListBeans);
                    }
                }));
    }

    /**
     * 开始topViewPager的轮转播放
     */
    @Override
    public void startInterval() {
        if (intervalDisposable != null && !intervalDisposable.isDisposed()) {
            return;
        }
        intervalDisposable = Flowable.interval(4, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mView.changeTopPageView();
                    }
                });
        addSubscribe(intervalDisposable);
    }

    /**
     * 停止topViewPager的轮转播放
     */
    @Override
    public void stopInterval() {
        if (intervalDisposable != null && !intervalDisposable.isDisposed()) {
            intervalDisposable.dispose();
        }
    }


}
