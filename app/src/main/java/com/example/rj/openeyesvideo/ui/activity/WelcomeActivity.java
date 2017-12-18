package com.example.rj.openeyesvideo.ui.activity;


import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.WelcomeContract;
import com.example.rj.openeyesvideo.presenter.WelcomePresenter;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity<WelcomePresenter>implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    @Override
    public void showContent(int image) {
        //ivWelcomeBg.setImageResource(image);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
    }

    @Override
    public void jumpToMain() {
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        //Glide.clear(ivWelcomeBg);
        super.onDestroy();
    }
}
