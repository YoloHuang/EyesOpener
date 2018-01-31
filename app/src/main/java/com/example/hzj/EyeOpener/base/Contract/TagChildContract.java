package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/26.
 */

public interface TagChildContract {
    interface View extends BaseView{
        void showContents(List<ItemListBean> itemListBeans);
        void showMoreContents(List<ItemListBean> itemListBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getTagChildData(int id);
        void getMoreData(int id);
    }
}
