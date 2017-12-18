package com.example.rj.openeyesvideo.base;

/**
 * Created by rj on 2017/12/18.
 */

public interface BaseView {
    void showErrorMsg(String s);

    //----state-----
    void stateError();
    void stateStart();
    void stateLoading();
}
