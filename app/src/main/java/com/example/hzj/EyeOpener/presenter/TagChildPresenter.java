package com.example.hzj.EyeOpener.presenter;

import android.util.Log;

import com.example.hzj.EyeOpener.base.Contract.TagChildContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.TagChildBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 */

public class TagChildPresenter extends RxPresenter<TagChildContract.View> implements TagChildContract.Presenter {


    int totalItems;
    List<ItemListBean> listBeans;

    @Inject
    public TagChildPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 根据tag id获取该分类下数据
     *
     * @param id
     */
    @Override
    public void getTagChildData(int id) {
        totalItems = 10;
        addSubscribe(mDataManager.getTagChildBean(0, 10, id)
                .compose(RxUtil.<TagChildBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<TagChildBean>(mView) {
                    @Override
                    public void onNext(TagChildBean tagChildBean) {
                        totalItems = tagChildBean.getCount();
                        listBeans = tagChildBean.getItemList();
                        Log.d("hzj", "onNext: listBeans" + listBeans + "totalItems:" + totalItems);
                        mView.showContents(listBeans);
                    }
                }));
    }

    /**
     * 获取该分类下更多数据
     *
     * @param id
     */
    @Override
    public void getMoreData(int id) {
        addSubscribe(mDataManager.getTagChildBean(totalItems, 10, id)
                .compose(RxUtil.<TagChildBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<TagChildBean>(mView) {
                    @Override
                    public void onNext(TagChildBean tagChildBean) {
                        totalItems = totalItems + tagChildBean.getCount();
                        listBeans.clear();
                        listBeans.addAll(tagChildBean.getItemList());
                        mView.showMoreContents(listBeans);
                    }
                }));
    }
}
