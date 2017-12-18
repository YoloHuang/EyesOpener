package com.example.rj.openeyesvideo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.rj.openeyesvideo.APP.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by rj on 2017/12/18.
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Context mContext;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
