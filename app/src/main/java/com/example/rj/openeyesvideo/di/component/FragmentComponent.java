package com.example.rj.openeyesvideo.di.component;

import android.app.Activity;

import com.example.rj.openeyesvideo.di.module.FragmentModule;
import com.example.rj.openeyesvideo.di.scope.FragmentScope;
import com.example.rj.openeyesvideo.ui.fragment.AllHotFragment;
import com.example.rj.openeyesvideo.ui.fragment.DailyFragment;
import com.example.rj.openeyesvideo.ui.fragment.HotFragment;
import com.example.rj.openeyesvideo.ui.fragment.MonthFragment;
import com.example.rj.openeyesvideo.ui.fragment.TagsFragment;
import com.example.rj.openeyesvideo.ui.fragment.BaseHotFragment;
import com.example.rj.openeyesvideo.ui.fragment.WeekFragment;

import dagger.Component;

/**
 * Created by rj on 2017/12/18.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
    void inject(DailyFragment dailyFragment);
    void inject(HotFragment hotFragment);
    void inject(TagsFragment tagsFragment);
    void inject(WeekFragment weekFragment);
    void inject(MonthFragment MonthFragment);
    void inject(AllHotFragment AllHotFragment);

}
