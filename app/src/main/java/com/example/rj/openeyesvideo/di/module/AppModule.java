package com.example.rj.openeyesvideo.di.module;

import com.example.rj.openeyesvideo.APP.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
