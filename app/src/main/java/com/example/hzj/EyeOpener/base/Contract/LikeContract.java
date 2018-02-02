package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;

import java.util.List;

/**
 * Created by hzj on 2018/1/9.
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
