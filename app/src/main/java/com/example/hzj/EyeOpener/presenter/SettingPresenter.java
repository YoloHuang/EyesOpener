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
    public SettingPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 获取关于是否使用流量播放的设置
     *
     * @return
     */
    @Override
    public boolean getPlaySetting() {
        return mDataManager.getPlaySetting();
    }

    /**
     * 设置是否使用流量观看
     *
     * @param playSetting
     */
    @Override
    public void setPlaySetting(boolean playSetting) {
        mDataManager.setPlaySetting(playSetting);
    }

    /**
     * 获取是否使用流量下载的设置
     *
     * @return
     */
    @Override
    public boolean getDownloadSetting() {
        return mDataManager.getDowmloadSetting();
    }

    /**
     * 设置是否使用流量下载
     *
     * @param downloadSetting
     */
    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mDataManager.setDownloadSetting(downloadSetting);
    }

}
