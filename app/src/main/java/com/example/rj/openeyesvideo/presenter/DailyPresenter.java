package com.example.rj.openeyesvideo.presenter;

import android.util.Log;

import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by rj on 2017/12/20.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    List<ItemListBean> itemListBeans;
    List<ItemListBean> moreItemListBeans;
    List<ItemListBean> firstItemListBeans;
    String nextUrl;
    long nextDate;


    @Inject
    public DailyPresenter(DataManager manager){
        this.mDataManager=manager;
    }

    @Override
    public void getDailyData() {
        Log.d("hzj", "getDailyData: ");
        addSubscribe(mDataManager.getDailyBean()
        .compose(RxUtil.<DailyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
            @Override
            public void onNext(DailyBean dailyBean) {
                Log.d("hzj", "onNext: "+dailyBean.getIssueList().size());
                nextUrl=dailyBean.getNextPageUrl();
                String nextDateName=nextUrl.substring(nextUrl.indexOf("=")+1,nextUrl.indexOf("&"));
                nextDate=Long.decode(nextDateName);
                firstItemListBeans=dailyBean.getIssueList().get(0).getItemList();
                mView.showFirstContent(firstItemListBeans);
                if(null!=dailyBean.getIssueList().get(1)){
                    itemListBeans=dailyBean.getIssueList().get(1).getItemList();
                }
                mView.showContent(itemListBeans);
            }
        }));
    }

    @Override
    public void getMoreData() {
        addSubscribe(mDataManager.getDailyBean(nextDate)
        .compose(RxUtil.<DailyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
            @Override
            public void onNext(DailyBean dailyBean) {
                nextUrl=dailyBean.getNextPageUrl();
                String nextDateName=nextUrl.substring(nextUrl.indexOf("=")+1,nextUrl.indexOf("&"));
                nextDate=Long.decode(nextDateName);
                moreItemListBeans=dailyBean.getIssueList().get(0).getItemList();
                if(null!=dailyBean.getIssueList().get(1)){
                    moreItemListBeans.addAll(dailyBean.getIssueList().get(1).getItemList());
                }
                mView.showContent(moreItemListBeans);
            }
        }));
    }

}
