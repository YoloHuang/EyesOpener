package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.DownloadBean;

import java.util.List;

/**
 * Created by hzj on 2018/1/22.
 */

public interface DownloadContract {
    interface View extends BaseView{
        void showContent(List<DownloadBean> downloadBeans);
        void goToDetail(List<DownloadBean> downloadBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getDownloadData();
    }

}
