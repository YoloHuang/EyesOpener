package com.example.rj.openeyesvideo.presenter;

import android.util.Log;

import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.Contract.DetailContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.RelateBean;
import com.example.rj.openeyesvideo.model.bean.ReplyBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/27.
 */

public class DetailPresenter extends RxPresenter<DetailContract.View> implements DetailContract.Presenter {


    @Inject
    public DetailPresenter(DataManager manager){
        this.mDataManager=manager;
    }

    @Override
    public void getVedioData(int id) {
        addSubscribe(mDataManager.getRelateBean(id)
        .compose(RxUtil.<RelateBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<RelateBean>(mView) {

            @Override
            public void onNext(RelateBean relateBean) {
                mView.showContent(relateBean.getItemList());
            }
        }));
    }

    @Override
    public void getReplyData(int id) {
        addSubscribe(mDataManager.getReplyBean(id)
        .compose(RxUtil.<ReplyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<ReplyBean>(mView) {
            @Override
            public void onNext(ReplyBean replyBean) {
                mView.showReply(replyBean);
            }
        }));
    }

    @Override
    public void addToHistory(ItemListBean itemListBean) {
        mDataManager.insertReadId(itemListBean);
    }

    @Override
    public boolean isRead(int id) {
        if(mDataManager.getHistoryBean(id)==null){
            return false;
        }else {
            Log.d("hzj", "isRead: mDataManager.getHistoryBean(id)"+mDataManager.getHistoryBean(id).getId());
            return true;
        }
    }

    @Override
    public void isLike(int id) {
        boolean like= mDataManager.checkLike(id);
        mView.setlike(like);
    }

    @Override
    public void deleteLikeId(int id) {
        mDataManager.deleteLikeId(id);
    }

    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        mDataManager.insertLikeId(itemListBean);
    }


}
