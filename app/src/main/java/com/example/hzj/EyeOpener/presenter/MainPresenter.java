package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.MainContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/20.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {



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
