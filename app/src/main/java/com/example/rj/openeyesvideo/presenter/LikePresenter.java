package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.LikeContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2018/1/9.
 */

public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter {

    List<LikeBean> likeBeans;
    ItemListBean.DataBean dataBean;

    @Inject
    public LikePresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

    @Override
    public void getLikeData() {
        likeBeans=mDataManager.getLikeBeans();
        mView.showContent(likeBeans);
    }

    @Override
    public void getDataBean(int id) {
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
