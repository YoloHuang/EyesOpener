package com.example.hzj.EyeOpener.base;

/**
 * Created by hzj on 2017/12/18.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
