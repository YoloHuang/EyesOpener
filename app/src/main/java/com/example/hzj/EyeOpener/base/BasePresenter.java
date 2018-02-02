package com.example.hzj.EyeOpener.base;

/**
 * Created by hzj on 2017/12/18.
 * 基础presenter类，完成与View的绑定关联。在Contract中对BasePresenter进行更进一步的接口拓展
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
