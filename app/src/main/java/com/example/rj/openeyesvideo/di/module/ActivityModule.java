package com.example.rj.openeyesvideo.di.module;

import android.app.Activity;

import com.example.rj.openeyesvideo.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rj on 2017/12/18.
 */
@Module
public class ActivityModule {
    private Activity activity;
    public  ActivityModule(Activity  activity){
        this.activity=activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){return activity;}
}
