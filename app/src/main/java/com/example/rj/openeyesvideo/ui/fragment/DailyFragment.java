package com.example.rj.openeyesvideo.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.RootFragment;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.presenter.DailyPresenter;
import com.example.rj.openeyesvideo.ui.activity.DetailActivity;
import com.example.rj.openeyesvideo.ui.adapter.BaseRecyclerAdapter;
import com.example.rj.openeyesvideo.ui.adapter.DailyRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by rj on 2017/12/20.
 */

public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.view_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH);

    private List<ItemListBean> itemListBeans = new ArrayList<>();
    private List<ItemListBean> firstItemListBeans=new ArrayList<>();

    LinearLayoutManager mLayoutManager;

    DailyRecyclerAdapter mAdapter;

    boolean isLoading=false;
    boolean dataReady=false;
    String TAG="hzj";


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        Log.d(TAG, "initEventAndData: ");
        super.initEventAndData();
        initRecyclerView();
        initSwipeRefresh();
        stateLoading();
        toolbar.setBackgroundColor(0x00000000);
        toolbarTitle.setText("");
        toolbarSearch.setImageResource(R.mipmap.ic_action_search_white);
    }

    private void initRecyclerView() {
        mLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new DailyRecyclerAdapter(mContext,itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getDailyData();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setToolBar();
                int lastItemPositon= mLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions=mLayoutManager.getItemCount();
                if(lastItemPositon>=totalPotions-4 && dy>0 && totalPotions>5){
                    if(isLoading){

                    }else {
                        isLoading=true;
                        mPresenter.getMoreData();
                    }
                }
            }
        });
        mAdapter.setOnItemClickListener(new DailyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent=new Intent();
                intent.setClass(mContext, DetailActivity.class);
                ItemListBean itemListBean=itemListBeans.get(id-1);
                intent.putExtra("itemListBean",itemListBean);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        if(dataReady){
            mPresenter.startInterval();
        }
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        mPresenter.stopInterval();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
        
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d(TAG, "onLazyInitView: ");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: "+isVisibleToUser);
        if(isVisibleToUser){
            if(dataReady){
                mPresenter.startInterval();
            }
        }else {
            if(mPresenter!=null){
                mPresenter.stopInterval();
            }
        }

    }

    private void setToolBar() {
        int firstItemPosition=mLayoutManager.findFirstVisibleItemPosition();
        if(firstItemPosition==0){
            mPresenter.startInterval();
            toolbar.setBackgroundColor(0x00000000);
            toolbarTitle.setText("");
            toolbarSearch.setImageResource(R.mipmap.ic_action_search_white);
        }else {
            mPresenter.stopInterval();
            if(itemListBeans.size()>1){
                ItemListBean itemListBean=itemListBeans.get(firstItemPosition-1);
                toolbar.setBackgroundColor(Color.WHITE);
                toolbarSearch.setImageResource(R.mipmap.ic_action_search);
                Log.d("hzj", "setToolBar: itemListBean=="+itemListBean.getData().getText()+"getType"+itemListBean.getType());
                if(itemListBean.getType().equals("textHeader")){
                    Log.d("hzj", "setToolBar: "+itemListBean.getData().getText());
                    toolbarTitle.setText(itemListBean.getData().getText());
                }else {
                    Log.d("hzj", "setToolBar:setText "+simpleDateFormat.format(itemListBean.getData().getDate()));
                    toolbarTitle.setText(simpleDateFormat.format(itemListBean.getData().getDate()));
                }
            }
        }
    }


    private void initSwipeRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLACK,Color.BLACK,Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }


    @Override
    public void showContent(List<ItemListBean> list) {
        for(ItemListBean itemListBean: list){
            if(itemListBean.getType().equals("textHeader")||itemListBean.getType().equals("video")) {
                itemListBeans.add(itemListBean);
            }
        }
        stateStart();
        isLoading=false;
        dataReady=true;
        mAdapter.addDailyData(itemListBeans);
        mPresenter.startInterval();
    }

    @Override
    public void showFirstContent(List<ItemListBean> listBeans) {
        for(ItemListBean itemListBean :listBeans){
            if(itemListBean.getType().equals("video")){
                firstItemListBeans.add(itemListBean);
            }
        }
        mAdapter.addTopData(firstItemListBeans);
    }

    @Override
    public void changeTopPageView(int item) {
        mAdapter.changeTopPageView(item);
    }

}
