package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.ReplyBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/27.
 */

public interface DetailContract {
    interface View extends BaseView {
        void showContent(List<ItemListBean> itemListBeans);

        void setlike(boolean like);

        void showReply(ReplyBean replyBean);

        void showMoreReply(ReplyBean replyBean);

        void showDownload();

        void showIsDownload();

        void showHadDownload();

        void showDownloadDialog();
    }

    interface Presenter extends BasePresenter<View> {
        void getVedioData(int id);

        void getReplyData(int id);

        void getMoreReplyData(int id);

        void addToHistory(ItemListBean itemListBean);

        boolean isRead(int id);

        void isLike(int id);

        void deleteLikeId(int id);

        void deleteReadId(int id);

        void insertLikeId(ItemListBean itemListBean);

        void download(String url, ItemListBean itemListBean);

        boolean getPlaySetting();

        void setPlaySetting(boolean playSetting);

        boolean getDownloadSetting();

        void setDownloadSetting(boolean downloadSetting);
    }
}
