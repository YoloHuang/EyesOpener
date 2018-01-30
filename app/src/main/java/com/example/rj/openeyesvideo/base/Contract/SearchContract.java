package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;

import java.util.List;

/**
 * Created by rj on 2018/1/25.
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
