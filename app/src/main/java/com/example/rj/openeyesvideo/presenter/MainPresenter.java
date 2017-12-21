package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.MainContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/20.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager){
        mDataManager=dataManager;
    }

    @Override
    public void checkPremission() {

    }

    @Override
    public void getSearchSuggestions() {
       // addSubscribe(mDataManager.getTrendingTagInfo()
       // .compose());
    }

}
