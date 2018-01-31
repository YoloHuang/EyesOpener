package com.example.hzj.EyeOpener.base;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.di.component.ActivityComponent;
import com.example.hzj.EyeOpener.di.component.DaggerActivityComponent;
import com.example.hzj.EyeOpener.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/18.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {


    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(getActivityModule()).build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }



    @Override
    protected void onViewCreate() {
        super.onViewCreate();
        initInject();
        if(mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String s) {

    }

    protected abstract void initInject();

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateStart() {

    }
}
