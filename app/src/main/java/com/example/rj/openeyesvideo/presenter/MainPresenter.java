package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.MainContract;
import com.example.rj.openeyesvideo.base.RxPresenter;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/20.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(){}

    @Override
    public void checkPremission() {

    }

}
