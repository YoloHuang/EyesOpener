package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.DownloadContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.DownloadBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2018/1/22.
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
