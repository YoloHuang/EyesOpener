package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.ReplyBean;

import java.util.List;

/**
 * Created by rj on 2017/12/27.
 */

public interface DetailContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> itemListBeans);
        void  setlike(boolean like);
        void showReply(ReplyBean replyBean);
        void showMoreReply(ReplyBean replyBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getVedioData(int id);
        void getReplyData(int id);
        void getMoreReplyData(int id );
        void addToHistory(ItemListBean itemListBean);
        boolean isRead(int id);
        void isLike(int id);
        void deleteLikeId(int id);
        void deleteReadId(int id);
        void insertLikeId(ItemListBean itemListBean);
    }
}
