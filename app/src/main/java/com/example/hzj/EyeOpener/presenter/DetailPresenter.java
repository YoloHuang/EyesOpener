package com.example.hzj.EyeOpener.presenter;

import android.util.Log;

import com.example.hzj.EyeOpener.base.Contract.DetailContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.RelateBean;
import com.example.hzj.EyeOpener.model.bean.ReplyBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/27.
 */

public class DetailPresenter extends RxPresenter<DetailContract.View> implements DetailContract.Presenter {


    String NextUrl;

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
                NextUrl=replyBean.getNextPageUrl();
            }
        }));
    }

    @Override
    public void getMoreReplyData(int id) {
        if(NextUrl==null){
            return;
        }else {
            String lastIdName=NextUrl.substring(NextUrl.indexOf("=")+1,NextUrl.indexOf("&"));
            Log.d("hzj", "getMoreReplyData: "+lastIdName);
            int lastId= Integer.valueOf(lastIdName).intValue();
            addSubscribe(mDataManager.getMoreReplyBean(id,lastId,10)
            .compose(RxUtil.<ReplyBean>rxSchedulerHelper())
            .subscribeWith(new CommonSubscriber<ReplyBean>(mView) {
                @Override
                public void onNext(ReplyBean replyBean) {
                    mView.showMoreReply(replyBean);
                    NextUrl=replyBean.getNextPageUrl();
                }
            }));
        }
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
    public void deleteReadId(int id) {
        mDataManager.deleteReadId(id);
    }

    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        mDataManager.insertLikeId(itemListBean);
    }

    @Override
    public void download(String url, ItemListBean itemListBean) {
        switch (mDataManager.checkDownload(itemListBean.getData().getId())){
            case 0:
                mDataManager.insertDownloadId(itemListBean);
                mDataManager.download(url,itemListBean);
                mView.showDownload();
                break;
            case 1:
                mView.showIsDownload();
                break;
            case 2:
                mView.showHadDownload();
                break;
        }
    }

    @Override
    public boolean getPlaySetting() {
        return mDataManager.getPlaySetting();
    }

    @Override
    public boolean getDownloadSetting() {
        return mDataManager.getDowmloadSetting();
    }

    @Override
    public void setPlaySetting(boolean playSetting) {
        mDataManager.setPlaySetting(playSetting);
    }

    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mDataManager.setDownloadSetting(downloadSetting);
    }


}
