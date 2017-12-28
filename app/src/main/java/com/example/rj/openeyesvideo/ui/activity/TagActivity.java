package com.example.rj.openeyesvideo.ui.activity;


import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.Contract.TagChildContract;
import com.example.rj.openeyesvideo.base.RootActivity;
import com.example.rj.openeyesvideo.base.SingleActivity;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.presenter.TagChildPresenter;
import com.example.rj.openeyesvideo.ui.adapter.TagChildAdapter;
import com.example.rj.openeyesvideo.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TagActivity extends SingleActivity<TagChildPresenter> implements TagChildContract.View {

    @BindView(R.id.view_main)
    RecyclerView rvThemeChildList;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.iv_theme_child_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_theme_child_des)
    TextView tvDes;
    @BindView(R.id.theme_child_appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;


    List<ItemListBean> mItemListBeans=new ArrayList<>();
    TagChildAdapter mAdapter;
    boolean isLoading=true;
    LinearLayoutManager mLinearLayoutManager;
     int tagId;


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        initIntent();
        //initRefreashView();
        stateLoading();
    }

//    private void initRefreashView() {
//        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset >= 0) {
//                    swipeRefresh.setEnabled(true);
//                } else {
//                    swipeRefresh.setEnabled(false);
//                    float rate = (float)(SystemUtil.dp2px(mContext, 256) + verticalOffset * 2) / SystemUtil.dp2px(mContext, 256);
//                    if (rate >= 0)
//                        ivOrigin.setAlpha(rate);
//                }
//            }
//        });
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.getTagChildData(tagId);
//            }
//        });
//    }

    private void initIntent() {
        Intent intent=getIntent();
        tagId=intent.getExtras().getInt("TagId");
        final String tagName=intent.getExtras().getString("TagName");
        final String TagImage=intent.getExtras().getString("TagImage");
        final String TagSlogen=intent.getExtras().getString("TagSlogen");
        toolbar_title.setText(tagName);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ImageLoader.load(mContext,TagImage,ivOrigin);
        tvDes.setText(TagSlogen);
        mPresenter.getTagChildData(tagId);
    }

    private void initRecyclerView() {
        mAdapter=new TagChildAdapter(mContext,mItemListBeans);
        mLinearLayoutManager=new LinearLayoutManager(mContext);
        rvThemeChildList.setLayoutManager(mLinearLayoutManager);
        rvThemeChildList.setAdapter(mAdapter);
        rvThemeChildList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon= mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions=mLinearLayoutManager.getItemCount();
                Log.d("hzj", "onScrolled: lastItemPositon:"+lastItemPositon+",totalPotions:"+totalPotions+";isloading:"+isLoading);
                if(lastItemPositon>=totalPotions-6 && dy>0 && totalPotions<=66){
                    if(isLoading){
                        Log.d("hzj", "onScrolled: "+isLoading);
                    }else {
                        isLoading=true;
                        mPresenter.getMoreData(tagId);
                    }
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tag;
    }

    @Override
    public void showContents(List<ItemListBean> itemListBeans) {
//        if(swipeRefresh.isRefreshing()) {
//            swipeRefresh.setRefreshing(false);
//        }
        mItemListBeans.clear();
        mItemListBeans.addAll(itemListBeans);
        Log.d("hzj", "showContents: mItemListBeans"+mItemListBeans.size());
        mAdapter.addTagChildData(mItemListBeans);
        stateStart();
        isLoading=false;
    }

    @Override
    public void showMoreContents(List<ItemListBean> itemListBeans) {
//        if(swipeRefresh.isRefreshing()) {
//            swipeRefresh.setRefreshing(false);
//        }
        mItemListBeans.addAll(itemListBeans);
        mAdapter.addTagChildData(mItemListBeans);
        stateStart();
        isLoading=false;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showErrorMsg(String s) {

    }
}
