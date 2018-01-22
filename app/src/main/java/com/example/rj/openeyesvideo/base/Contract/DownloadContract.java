package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.DownloadBean;

import java.util.List;

/**
 * Created by rj on 2018/1/22.
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
