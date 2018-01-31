package com.example.hzj.EyeOpener.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hzj.EyeOpener.APP.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by hzj on 2017/12/18.
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Context mContext;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        onViewCreate();
        unbinder= ButterKnife.bind(this);
        mContext=this;
        App.getApp().addActivity(this);
        initEventAndData();
    }

    protected void onViewCreate() {
    }

    protected abstract void initEventAndData();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        App.getApp().removeActivity(this);
    }


}
