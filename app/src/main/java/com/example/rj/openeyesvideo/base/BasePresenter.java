package com.example.rj.openeyesvideo.base;

/**
 * Created by rj on 2017/12/18.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
