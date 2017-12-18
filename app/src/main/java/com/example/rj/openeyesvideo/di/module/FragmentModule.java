package com.example.rj.openeyesvideo.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.example.rj.openeyesvideo.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rj on 2017/12/18.
 */
@Module
public class FragmentModule {
    private Fragment fragment;
    public FragmentModule(Fragment fragment){this.fragment=fragment;}

    @Provides
    @FragmentScope
    Activity provideActivity(){return fragment.getActivity();}
}
