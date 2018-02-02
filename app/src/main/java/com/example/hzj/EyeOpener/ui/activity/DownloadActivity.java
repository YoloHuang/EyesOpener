package com.example.hzj.EyeOpener.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.DownloadContract;
import com.example.hzj.EyeOpener.base.RootActivity;
import com.example.hzj.EyeOpener.model.bean.DownloadBean;
import com.example.hzj.EyeOpener.presenter.DownloadPresenter;
import com.example.hzj.EyeOpener.ui.adapter.DownloadAdapter;
import com.example.hzj.EyeOpener.ui.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DownloadActivity extends RootActivity<DownloadPresenter> implements DownloadContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    LinearLayoutManager linearLayoutManager;
    DownloadAdapter mAdapter;
    List<DownloadBean> downloadBeans = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        title.setText("我的缓存");
        toolbarSearch.setVisibility(View.GONE);
        stateLoading();
        initRecyclerView();
        mPresenter.getDownloadData();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new DownloadAdapter(mContext, downloadBeans);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {

            }
        });
    }

    @Override
    public void showContent(List<DownloadBean> bean) {
        stateStart();
        this.downloadBeans = bean;
        mAdapter.addDownloadData(downloadBeans);
    }

    @Override
    public void goToDetail(List<DownloadBean> downloadBeans) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_download;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
