package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by rj on 2018/1/8.
 */

public interface HistroyContract {
    interface View extends BaseView{
        void showContent(List<HistoryBean> historyBeans);
        void goToDetail(ItemListBean.DataBean dataBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getHistoryData();
        void getDataBean(int id);
    }
}
