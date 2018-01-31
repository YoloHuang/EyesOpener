package com.example.hzj.EyeOpener.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.APP.Constants;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/31.
 */

public class ImplPreferenceHelper implements PreferenceHelper {

    private static final Boolean DEFAULT_DOWNLOADSETTING=false;
    private static final Boolean DEFAULT_PLAYSETTING=false;

    private final SharedPreferences preferences;

    @Inject
    public ImplPreferenceHelper(){
        preferences = App.getApp().getSharedPreferences("my_oe", Context.MODE_PRIVATE);
    }


    @Override
    public boolean getPlaySetting() {
        return preferences.getBoolean(Constants.PLAYSETTING,DEFAULT_PLAYSETTING);
    }

    @Override
    public boolean getDowmloadSetting() {
        return preferences.getBoolean(Constants.DOWNLOADSETTING,DEFAULT_DOWNLOADSETTING);
    }

    @Override
    public void setPlaySetting(boolean playSetting) {
        preferences.edit().putBoolean(Constants.PLAYSETTING,playSetting).apply();
    }

    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        preferences.edit().putBoolean(Constants.DOWNLOADSETTING,downloadSetting).apply();
    }
}
