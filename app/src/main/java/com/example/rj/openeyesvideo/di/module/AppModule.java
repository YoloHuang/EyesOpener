package com.example.rj.openeyesvideo.di.module;

import com.example.rj.openeyesvideo.APP.App;
import com.example.rj.openeyesvideo.model.DB.DBHelper;
import com.example.rj.openeyesvideo.model.DB.RealmHelper;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.http.Api;
import com.example.rj.openeyesvideo.model.http.ApiHelper;
import com.example.rj.openeyesvideo.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.annotations.PrimaryKey;
import retrofit2.Retrofit;

/**
 * Created by rj on 2017/12/18.
 */
@Module
public class AppModule {
    private final App app;
    public AppModule(App app){this.app=app;}

    @Provides
    @Singleton
    App provideApp(){return app;}

    //因为dataManager构造方法中有ApiHelper,所以这里需要provide ApiHelper
    @Provides
    @Singleton
    ApiHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApiHelper apiHelper,DBHelper dbHelper){
        return new DataManager(apiHelper,dbHelper);
    }

}
