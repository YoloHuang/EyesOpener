package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.Contract.DetailContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/27.
 */

public class DetailPresenter extends RxPresenter<DetailContract.View> implements DetailContract.Presenter {


    @Inject
    public DetailPresenter(DataManager manager){
        this.mDataManager=manager;
    }

    @Override
    public void getVedioData() {

    }
}
