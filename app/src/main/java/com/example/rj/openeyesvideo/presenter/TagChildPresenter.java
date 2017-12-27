package com.example.rj.openeyesvideo.presenter;

import android.util.Log;

import com.example.rj.openeyesvideo.base.Contract.TagChildContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;
import com.example.rj.openeyesvideo.model.bean.TagsBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/26.
 */

public class TagChildPresenter extends RxPresenter<TagChildContract.View> implements TagChildContract.Presenter {


    int totalItems;
    List<ItemListBean> listBeans;

    @Inject
    public TagChildPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

    @Override
    public void getTagChildData(int id) {
        totalItems=10;
        addSubscribe(mDataManager.getTagChildBean(0,10,id)
        .compose(RxUtil.<TagChildBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<TagChildBean>(mView) {
            @Override
            public void onNext(TagChildBean tagChildBean) {
                totalItems=tagChildBean.getCount();
                listBeans=tagChildBean.getItemList();
                Log.d("hzj", "onNext: listBeans"+listBeans+"totalItems:"+totalItems);
                mView.showContents(listBeans);
            }
        }));
    }

    @Override
    public void getMoreData(int id) {
        addSubscribe(mDataManager.getTagChildBean(totalItems,10,id)
        .compose(RxUtil.<TagChildBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<TagChildBean>(mView) {
            @Override
            public void onNext(TagChildBean tagChildBean) {
                totalItems=totalItems+tagChildBean.getCount();
                listBeans.clear();
                listBeans.addAll(tagChildBean.getItemList());
                mView.showMoreContents(listBeans);
            }
        }));
    }
}
