package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.LikeContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/9.
 */

public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter {

    List<LikeBean> likeBeans;

    @Inject
    public LikePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 获取所有like数据
     */
    @Override
    public void getLikeData() {
        likeBeans = mDataManager.getLikeBeans();
        mView.showContent(likeBeans);
    }

    /**
     * 根据ID，获取DataBean
     *
     * @param id
     */
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
