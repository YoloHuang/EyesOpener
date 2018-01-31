package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by hzj on 2018/1/25.
 */

public interface SearchContract {
    interface View extends BaseView{
        void  showHotSearch(List<String> stringList);
        void  showResult(List<ItemListBean> listBeans,int total);
        void showMoreResult(List<ItemListBean> listBeans);
        void addProgressView();
    }
    interface Presenter extends BasePresenter<View>{
        void getHotSearchData();
        void getSearchData(String query);
        void getMoreData(String query);
    }
}
