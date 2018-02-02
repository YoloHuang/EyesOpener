package com.example.hzj.EyeOpener.di.component;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.di.module.AppModule;
import com.example.hzj.EyeOpener.di.module.HttpModule;
import com.example.hzj.EyeOpener.model.DB.RealmHelper;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hzj on 2017/12/18.
 * 提供全局单例类的实例
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();

    RetrofitHelper retrofitHelper();

    RealmHelper realmHelper();

    DataManager getDataManager();
}
