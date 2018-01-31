package com.example.hzj.EyeOpener.model.prefs;

/**
 * Created by hzj on 2018/1/31.
 */

public interface PreferenceHelper {

    boolean getPlaySetting();

    boolean getDowmloadSetting();

    void setPlaySetting(boolean playSetting);

    void setDownloadSetting(boolean downloadSetting);

}
