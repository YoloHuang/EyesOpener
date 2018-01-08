package com.example.rj.openeyesvideo.presenter;

import android.util.Log;

import com.example.rj.openeyesvideo.base.Contract.HistroyContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2018/1/8.
 */

public class HistoryPresenter extends RxPresenter<HistroyContract.View> implements HistroyContract.Presenter {


    List<HistoryBean> historyBeans;
    ItemListBean.DataBean dataBeans;

    @Inject
    public HistoryPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }


    @Override
    public void getHistoryData() {
        historyBeans=mDataManager.getHistoryBeans();
        mView.showContent(historyBeans);
    }

    @Override
    public void getDataBean(int id) {
        Log.d("hzj", "getDataBean: id"+id);
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
