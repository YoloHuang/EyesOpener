package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by hzj on 2018/1/8.
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
