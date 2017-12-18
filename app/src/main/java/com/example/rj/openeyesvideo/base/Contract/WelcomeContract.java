package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;

/**
 * Created by rj on 2017/12/18.
 */

public interface WelcomeContract {
    interface View extends BaseView{
        void showContent(int image);
        void jumpToMain();
    }
    interface Presenter extends BasePresenter<View>{
        void getData();
    }
}
