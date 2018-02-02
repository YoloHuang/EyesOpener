package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;

/**
 * Created by hzj on 2017/12/18.
 */

public interface WelcomeContract {
    interface View extends BaseView {
        void showContent(int image);

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
