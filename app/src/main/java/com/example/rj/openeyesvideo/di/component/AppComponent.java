package com.example.rj.openeyesvideo.di.component;

import com.example.rj.openeyesvideo.APP.App;
import com.example.rj.openeyesvideo.di.module.AppModule;
import com.example.rj.openeyesvideo.di.module.HttpModule;
import com.example.rj.openeyesvideo.model.DB.RealmHelper;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rj on 2017/12/18.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();

    RetrofitHelper retrofitHelper();
    RealmHelper realmHelper();
    DataManager getDataManager();
}
