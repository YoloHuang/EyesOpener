package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.HotContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 * HotFragment所关联类
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    @Inject
    public HotPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

}
