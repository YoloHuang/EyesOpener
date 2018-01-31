package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/20.
 */

public interface DailyContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> list);
        void showFirstContent(List<ItemListBean> listBeans);
        void changeTopPageView(int item);
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getMoreData();
        void startInterval();
        void stopInterval();
    }
}
