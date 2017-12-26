package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.HotContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/26.
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    @Inject
    public HotPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

}
