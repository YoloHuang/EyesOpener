package com.example.hzj.EyeOpener.ui.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.LikeContract;
import com.example.hzj.EyeOpener.base.RootActivity;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;
import com.example.hzj.EyeOpener.presenter.LikePresenter;
import com.example.hzj.EyeOpener.ui.adapter.HistoryAdapter;
import com.example.hzj.EyeOpener.ui.adapter.LikeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LikeActivity extends RootActivity<LikePresenter> implements LikeContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    LinearLayoutManager linearLayoutManager;
    LikeAdapter mAdapter;
    List<LikeBean> LikeBeans = new ArrayList<>();


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        title.setText("我的喜欢");
        toolbarSearch.setVisibility(View.GONE);
        stateLoading();
        initRecyclerView();
        mPresenter.getLikeData();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new LikeAdapter(mContext, LikeBeans);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                mPresenter.getDataBean(LikeBeans.get(id).getId());

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_history;
    }


    @Override
    public void showContent(List<LikeBean> LikeBeans) {
        stateStart();
        this.LikeBeans = LikeBeans;
        mAdapter.addLikeData(LikeBeans);
    }

    /**
     * 跳转到DetailActivity界面
     *
     * @param dataBean
     */
    @Override
    public void goToDetail(ItemListBean.DataBean dataBean) {
        ItemListBean itemListBean = new ItemListBean();
        itemListBean.setData(dataBean);
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("itemListBean", itemListBean);
        mContext.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
