package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;

import java.util.List;

/**
 * Created by rj on 2017/12/20.
 */

public interface MainContract {
    interface View extends BaseView{
        void setSearchDisable();
        void showSearchSuggestions(List<String> strings);
    }
    interface Presenter extends BasePresenter<View>{
        void checkPremission();
        void getSearchSuggestions();
    }
}
