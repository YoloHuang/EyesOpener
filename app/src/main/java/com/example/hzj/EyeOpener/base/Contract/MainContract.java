package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;

/**
 * Created by hzj on 2017/12/20.
 */

public interface MainContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        void checkPremission();

        void getSearchSuggestions();
    }
}
