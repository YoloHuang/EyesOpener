package com.example.hzj.EyeOpener.ui.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.HistroyContract;
import com.example.hzj.EyeOpener.base.RootActivity;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.presenter.HistoryPresenter;
import com.example.hzj.EyeOpener.ui.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends RootActivity<HistoryPresenter> implements HistroyContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    LinearLayoutManager linearLayoutManager;
    HistoryAdapter mAdapter;
    List<HistoryBean> historyBeans=new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        title.setText("观看记录");
        toolbarSearch.setVisibility(View.GONE);
        stateLoading();
        initRecyclerView();
        mPresenter.getHistoryData();

    }

    private void initRecyclerView() {
        linearLayoutManager=new LinearLayoutManager(mContext);
        mAdapter=new HistoryAdapter(mContext,historyBeans);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                mPresenter.getDataBean(historyBeans.get(id).getId());

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void showContent(List<HistoryBean> beans) {
        stateStart();
        historyBeans=beans;
        Log.d("hzj", "showContent: historyBeans"+historyBeans.size());
        mAdapter.addHistoryData(historyBeans);
    }

    @Override
    public void goToDetail(ItemListBean.DataBean dataBean) {
        ItemListBean itemListBean=new ItemListBean();
        itemListBean.setData(dataBean);
        Intent intent=new Intent();
        intent.setClass(mContext,DetailActivity.class);
        intent.putExtra("itemListBean",itemListBean);
        mContext.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
