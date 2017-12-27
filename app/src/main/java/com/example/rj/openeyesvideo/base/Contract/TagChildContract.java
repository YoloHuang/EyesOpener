package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;
import com.example.rj.openeyesvideo.model.bean.TagsBean;

import java.util.List;

/**
 * Created by rj on 2017/12/26.
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
