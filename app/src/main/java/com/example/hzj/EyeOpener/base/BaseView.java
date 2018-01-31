package com.example.hzj.EyeOpener.base;

/**
 * Created by hzj on 2017/12/18.
 */

public interface BaseView {
    void showErrorMsg(String s);

    //----state-----
    void stateError();
    void stateStart();
    void stateLoading();
}
