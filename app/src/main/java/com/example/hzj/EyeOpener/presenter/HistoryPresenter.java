package com.example.hzj.EyeOpener.presenter;

import android.util.Log;

import com.example.hzj.EyeOpener.base.Contract.HistroyContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/8.
 * Historyactivity关联类
 */

public class HistoryPresenter extends RxPresenter<HistroyContract.View> implements HistroyContract.Presenter {


    List<HistoryBean> historyBeans;

    @Inject
    public HistoryPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 获取观看数据
     */
    @Override
    public void getHistoryData() {
        historyBeans = mDataManager.getHistoryBeans();
        mView.showContent(historyBeans);
    }

    /**
     * 根据ID，获取DataBean,用于最后调用DetailActivity
     *
     * @param id
     */
    @Override
    public void getDataBean(int id) {
        Log.d("hzj", "getDataBean: id" + id);
        addSubscribe(mDataManager.getDataBean(id)
                .compose(RxUtil.<ItemListBean.DataBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ItemListBean.DataBean>(mView) {
                    @Override
                    public void onNext(ItemListBean.DataBean dataBean) {
                        mView.goToDetail(dataBean);
                    }
                }));

    }
}
