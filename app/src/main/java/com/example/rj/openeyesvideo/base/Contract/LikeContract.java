package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import java.util.List;

/**
 * Created by rj on 2018/1/9.
 */

public interface LikeContract {
    interface View extends BaseView {
        void showContent(List<LikeBean> LikeBeans);
        void goToDetail(ItemListBean.DataBean dataBean);
    }
    interface Presenter extends BasePresenter<View> {
        void getLikeData();
        void getDataBean(int id);
    }
}
