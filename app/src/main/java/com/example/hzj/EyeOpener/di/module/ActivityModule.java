package com.example.hzj.EyeOpener.di.module;

import android.app.Activity;

import com.example.hzj.EyeOpener.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hzj on 2017/12/18.
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }
}
