package com.example.hzj.EyeOpener.APP;

import java.io.File;

/**
 * Created by hzj on 2017/12/21.
 */

public class Constants {
    //app所用存储空间
    public static final String PATH_DATA=App.getApp().getCacheDir().getAbsolutePath()+ File.separator+"data";
    public static final String PATH_CACHE=PATH_DATA+"/NetCache";
    public static final String PATH_DOWNLOAD=PATH_DATA+"/Download";

    //设置中sharedpreferenced的名称
    public static final String DOWNLOADSETTING="setting_download";
    public static final String PLAYSETTING="setting_play";
}
