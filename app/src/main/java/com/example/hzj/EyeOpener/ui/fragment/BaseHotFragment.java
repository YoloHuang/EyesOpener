package com.example.hzj.EyeOpener.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.HotTopContract;
import com.example.hzj.EyeOpener.base.RootFragment;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.presenter.WeekPresenter;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.example.hzj.EyeOpener.ui.adapter.HotTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/26.
 */

public abstract class BaseHotFragment extends RootFragment<WeekPresenter> implements HotTopContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    HotTopAdapter mAdapter;
    private List<ItemListBean> itemListBeans = new ArrayList<>();

    @Override
    public void showContents(List<ItemListBean> listBean) {
        for (ItemListBean itemListBean : listBean) {
            if (itemListBean.getType().equals("video")) {
                if (itemListBean.getData().getAuthor() != null) {
                    itemListBeans.add(itemListBean);
                }
            }
        }
        stateStart();
        mAdapter.addHotTopData(itemListBeans);
    }

    @Override
    protected void initInject() {
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        stateLoading();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HotTopAdapter(mContext, itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        getHotData();
        mAdapter.setOnItemClickListener(new HotTopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                ItemListBean itemListBean = itemListBeans.get(id);
                intent.putExtra("itemListBean", itemListBean);
                mContext.startActivity(intent);
            }
        });
    }

    protected abstract void getHotData();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hotitem;
    }

}
