package com.example.rj.openeyesvideo.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.presenter.DailyPresenter;

import butterknife.BindView;

/**
 * Created by rj on 2017/12/20.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.view_RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        initSwipeRefresh();
        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    private void initSwipeRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDailyData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateStart() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void showContent() {

    }
}
