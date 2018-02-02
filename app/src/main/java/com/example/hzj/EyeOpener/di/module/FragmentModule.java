package com.example.hzj.EyeOpener.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.hzj.EyeOpener.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hzj on 2017/12/18.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }
}
