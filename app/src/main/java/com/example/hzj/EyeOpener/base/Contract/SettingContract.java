package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;

/**
 * Created by hzj on 2018/1/31.
 */

public interface SettingContract {

    interface View extends BaseView{
       void  showCleanToast();
    }
    interface Presenter extends BasePresenter<View>{
        boolean getPlaySetting();
        boolean getDownloadSetting();
        void setPlaySetting(boolean playSetting);
        void setDownloadSetting(boolean downloadSetting);
        void cleanCache();
    }
}
