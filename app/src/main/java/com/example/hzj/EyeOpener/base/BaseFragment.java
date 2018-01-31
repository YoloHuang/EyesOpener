package com.example.hzj.EyeOpener.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.di.component.DaggerFragmentComponent;
import com.example.hzj.EyeOpener.di.component.FragmentComponent;
import com.example.hzj.EyeOpener.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/18.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView  {

    @Inject
    protected
    T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).fragmentModule(getFragmentModule()).build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
        mPresenter.detachView();}
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String s) {

    }

    protected abstract void initInject();
}
