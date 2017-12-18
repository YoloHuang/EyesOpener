package com.example.rj.openeyesvideo.di.component;

import android.app.Activity;

import com.example.rj.openeyesvideo.di.module.FragmentModule;
import com.example.rj.openeyesvideo.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by rj on 2017/12/18.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
