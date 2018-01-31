package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.DownloadContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.DownloadBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/22.
 */

public class DownloadPresenter extends RxPresenter<DownloadContract.View> implements DownloadContract.Presenter {

    List<DownloadBean> downloadBeans;

    @Inject
    public DownloadPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

    @Override
    public void getDownloadData() {
        downloadBeans=mDataManager.getDownloadBeans();
        mView.showContent(downloadBeans);
    }
}
