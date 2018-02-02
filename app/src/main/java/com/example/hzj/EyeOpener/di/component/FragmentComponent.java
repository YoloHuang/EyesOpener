package com.example.hzj.EyeOpener.di.component;

import android.app.Activity;

import com.example.hzj.EyeOpener.di.module.FragmentModule;
import com.example.hzj.EyeOpener.di.scope.FragmentScope;
import com.example.hzj.EyeOpener.ui.fragment.AllHotFragment;
import com.example.hzj.EyeOpener.ui.fragment.DailyFragment;
import com.example.hzj.EyeOpener.ui.fragment.HotFragment;
import com.example.hzj.EyeOpener.ui.fragment.MonthFragment;
import com.example.hzj.EyeOpener.ui.fragment.TagsFragment;
import com.example.hzj.EyeOpener.ui.fragment.WeekFragment;

import dagger.Component;

/**
 * Created by hzj on 2017/12/18.
 * FragmentComponent类
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(DailyFragment dailyFragment);

    void inject(HotFragment hotFragment);

    void inject(TagsFragment tagsFragment);

    void inject(WeekFragment weekFragment);

    void inject(MonthFragment MonthFragment);

    void inject(AllHotFragment AllHotFragment);

}
