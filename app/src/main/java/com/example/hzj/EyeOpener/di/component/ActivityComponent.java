package com.example.hzj.EyeOpener.di.component;

import android.app.Activity;

import com.example.hzj.EyeOpener.di.module.ActivityModule;
import com.example.hzj.EyeOpener.di.scope.ActivityScope;
import com.example.hzj.EyeOpener.ui.activity.AboutActivity;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.example.hzj.EyeOpener.ui.activity.DownloadActivity;
import com.example.hzj.EyeOpener.ui.activity.HistoryActivity;
import com.example.hzj.EyeOpener.ui.activity.LikeActivity;
import com.example.hzj.EyeOpener.ui.activity.MainActivity;
import com.example.hzj.EyeOpener.ui.activity.SearchActivity;
import com.example.hzj.EyeOpener.ui.activity.SettingActivity;
import com.example.hzj.EyeOpener.ui.activity.TagActivity;
import com.example.hzj.EyeOpener.ui.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by hzj on 2017/12/18.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
    void inject(MainActivity mainActivity);
    void inject(TagActivity tagActivity);
    void inject(DetailActivity detailActivity);
    void inject(HistoryActivity historyActivity);
    void inject(LikeActivity likeActivity);
    void inject(DownloadActivity downloadActivity);
    void inject(SearchActivity searchActivity);
    void inject(SettingActivity settingActivity);
    void inject(AboutActivity aboutActivity);
}
