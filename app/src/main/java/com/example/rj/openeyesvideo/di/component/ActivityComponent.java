package com.example.rj.openeyesvideo.di.component;

import android.app.Activity;

import com.example.rj.openeyesvideo.di.module.ActivityModule;
import com.example.rj.openeyesvideo.di.scope.ActivityScope;
import com.example.rj.openeyesvideo.ui.activity.DetailActivity;
import com.example.rj.openeyesvideo.ui.activity.MainActivity;
import com.example.rj.openeyesvideo.ui.activity.TagActivity;
import com.example.rj.openeyesvideo.ui.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by rj on 2017/12/18.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
    void inject(MainActivity mainActivity);
    void inject(TagActivity tagActivity);
    void inject(DetailActivity detailActivity);
}
