package com.example.rj.openeyesvideo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.rj.openeyesvideo.APP.App;
import com.example.rj.openeyesvideo.di.component.DaggerFragmentComponent;
import com.example.rj.openeyesvideo.di.component.FragmentComponent;
import com.example.rj.openeyesvideo.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/18.
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
