package com.example.hzj.EyeOpener.di.module;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.model.DB.DBHelper;
import com.example.hzj.EyeOpener.model.DB.RealmHelper;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.http.ApiHelper;
import com.example.hzj.EyeOpener.model.http.RetrofitHelper;
import com.example.hzj.EyeOpener.model.prefs.ImplPreferenceHelper;
import com.example.hzj.EyeOpener.model.prefs.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hzj on 2017/12/18.
 */
@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return app;
    }

    /**
     * 因为dataManager构造方法中有ApiHelper，RealmHelper，ImplPreferenceHelper,所以这里需要provide
     *
     * @param retrofitHelper
     * @return
     */
    @Provides
    @Singleton
    ApiHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper provideImplPreferenceHelper(ImplPreferenceHelper implPreferenceHelper) {
        return implPreferenceHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApiHelper apiHelper, DBHelper dbHelper, PreferenceHelper preferenceHelper) {
        return new DataManager(apiHelper, dbHelper, preferenceHelper);
    }

}
