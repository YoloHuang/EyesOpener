package com.example.rj.openeyesvideo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.Contract.DetailContract;
import com.example.rj.openeyesvideo.base.RootActivity;
import com.example.rj.openeyesvideo.presenter.DetailPresenter;

public class DetailActivity extends RootActivity<DetailPresenter> implements DetailContract.View {

    

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        getIntentData();
    }

    private void getIntentData() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void showContent() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
