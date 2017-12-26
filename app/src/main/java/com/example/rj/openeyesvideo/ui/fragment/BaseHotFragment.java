package com.example.rj.openeyesvideo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.Contract.HotTopContract;
import com.example.rj.openeyesvideo.base.RootFragment;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.presenter.WeekPresenter;
import com.example.rj.openeyesvideo.ui.adapter.HotTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by rj on 2017/12/26.
 */

public abstract class BaseHotFragment extends RootFragment<WeekPresenter> implements HotTopContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    private List<HotBean.ItemListBean> itemListBeans = new ArrayList<>();

    LinearLayoutManager mLayoutManager;

    HotTopAdapter mAdapter;


    @Override
    public void showContents(List<HotBean.ItemListBean> listBean) {
        for(HotBean.ItemListBean itemListBean: listBean){
            if (itemListBean.getType().equals("video")){
                itemListBeans.add(itemListBean);
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
        mLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new HotTopAdapter(mContext,itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        getHotData();
    }

    protected abstract void getHotData();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listcomment;
    }

}
