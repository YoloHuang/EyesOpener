package com.example.hzj.EyeOpener.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseActivity;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.base.RxPresenter;

public class AboutActivity extends BaseActivity<RxPresenter> implements BaseView {


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
