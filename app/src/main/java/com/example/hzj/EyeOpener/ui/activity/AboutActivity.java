package com.example.hzj.EyeOpener.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseActivity;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.presenter.AboutPresenter;

import butterknife.BindView;

public class AboutActivity extends BaseActivity<AboutPresenter> implements BaseView {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.ic_toolbar_search)
    ImageView search;


    @Override
    protected void initEventAndData() {
        title.setText("关于");
        search.setVisibility(View.GONE);
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
