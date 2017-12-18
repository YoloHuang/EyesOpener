package com.example.rj.openeyesvideo.di.component;

import com.example.rj.openeyesvideo.APP.App;
import com.example.rj.openeyesvideo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rj on 2017/12/18.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App getContext();
}
