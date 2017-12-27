package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.HotTopContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/26.
 */

public class WeekPresenter extends RxPresenter<HotTopContract.View> implements HotTopContract.Presenter {

    List<ItemListBean> itemListBeans;

    @Inject
    public WeekPresenter(DataManager dataManager){
        mDataManager=dataManager;
    }

    @Override
    public void getHotData(String type) {
        addSubscribe(mDataManager.getWeekHotBean(type)
        .compose(RxUtil.<HotBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<HotBean>(mView) {
            @Override
            public void onNext(HotBean hotBean) {
                itemListBeans=hotBean.getItemList();
                mView.showContents(itemListBeans);
            }
        }));
    }
}
