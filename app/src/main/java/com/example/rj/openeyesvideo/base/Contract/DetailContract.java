package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by rj on 2017/12/27.
 */

public interface DetailContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> itemListBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getVedioData(int id);
    }
}
