package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.RxPresenter;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/20.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    @Inject
    public DailyPresenter(){}

    @Override
    public void getDailyData() {

    }

    @Override
    public void getMoreData() {

    }
}
