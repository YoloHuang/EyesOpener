package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.SettingContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/31.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject
    public SettingPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

    @Override
    public boolean getPlaySetting() {
        return mDataManager.getPlaySetting();
    }

    @Override
    public boolean getDownloadSetting() {
        return mDataManager.getDowmloadSetting();
    }

    @Override
    public void setPlaySetting(boolean playSetting) {
        mDataManager.setPlaySetting(playSetting);
    }

    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mDataManager.setDownloadSetting(downloadSetting);
    }

    @Override
    public void cleanCache() {

    }
}
