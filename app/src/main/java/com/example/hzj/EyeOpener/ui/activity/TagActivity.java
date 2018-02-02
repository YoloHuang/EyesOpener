package com.example.hzj.EyeOpener.ui.activity;


import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.TagChildContract;
import com.example.hzj.EyeOpener.base.RootActivity;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.presenter.TagChildPresenter;
import com.example.hzj.EyeOpener.ui.adapter.TagChildAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TagActivity extends RootActivity<TagChildPresenter> implements TagChildContract.View {

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


    List<ItemListBean> mItemListBeans = new ArrayList<>();
    TagChildAdapter mAdapter;
    boolean isLoading = true;
    LinearLayoutManager mLinearLayoutManager;
    int tagId;


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        initIntent();
        stateLoading();
    }

    /**
     * 获取intent传递过来的数据
     */
    private void initIntent() {
        Intent intent = getIntent();
        tagId = intent.getExtras().getInt("TagId");
        final String tagName = intent.getExtras().getString("TagName");
        final String TagImage = intent.getExtras().getString("TagImage");
        final String TagSlogen = intent.getExtras().getString("TagSlogen");
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
        ImageLoader.load(mContext, TagImage, ivOrigin);
        tvDes.setText(TagSlogen);
        mPresenter.getTagChildData(tagId);
        mAdapter.setOnItemClickListener(new TagChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent childIntent = new Intent();
                ItemListBean itemListBean = mItemListBeans.get(id);
                childIntent.putExtra("itemListBean", itemListBean);
                childIntent.setClass(mContext, DetailActivity.class);
                mContext.startActivity(childIntent);
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new TagChildAdapter(mContext, mItemListBeans);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
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
                int lastItemPositon = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mLinearLayoutManager.getItemCount();
                if (lastItemPositon >= totalPotions - 6 && dy > 0 && totalPotions <= 66) {
                    if (isLoading) {
                    } else {
                        isLoading = true;
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
        mItemListBeans.clear();
        for (ItemListBean itemListBean : itemListBeans) {
            if (itemListBean.getType().equals("video")) {
                if (itemListBean.getData().getAuthor() != null) {
                    mItemListBeans.add(itemListBean);
                }
            }
        }
        mAdapter.addTagChildData(mItemListBeans);
        stateStart();
        isLoading = false;
    }

    @Override
    public void showMoreContents(List<ItemListBean> itemListBeans) {
        for (ItemListBean itemListBean : itemListBeans) {
            if (itemListBean.getType().equals("video")) {
                if (itemListBean.getData().getAuthor() != null) {
                    mItemListBeans.add(itemListBean);
                }
            }
        }
        mAdapter.addTagChildData(mItemListBeans);
        stateStart();
        isLoading = false;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showErrorMsg(String s) {

    }
}
