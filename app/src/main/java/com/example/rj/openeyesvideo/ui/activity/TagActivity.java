package com.example.rj.openeyesvideo.ui.activity;


import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.Contract.TagChildContract;
import com.example.rj.openeyesvideo.base.RootActivity;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;
import com.example.rj.openeyesvideo.model.bean.TagsBean;
import com.example.rj.openeyesvideo.presenter.TagChildPresenter;
import com.example.rj.openeyesvideo.ui.adapter.TagChildAdapter;
import com.example.rj.openeyesvideo.util.SystemUtil;

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


    List<TagChildBean.ItemListBean> itemListBeans=new ArrayList<>();
    TagChildAdapter mAdapter;


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Intent intent=getIntent();
        final int tagId=intent.getExtras().getInt("TagId");
        final String tagName=intent.getExtras().getString("TagName");
        final String TagImage=intent.getExtras().getString("TagImage");
        final String TagSlogen=intent.getExtras().getString("TagSlogen");
        toolbar_title.setText(tagName);
        ImageLoader.load(mContext,TagImage,ivOrigin);
        tvDes.setText(TagSlogen);
        mAdapter=new TagChildAdapter(mContext,itemListBeans);
        rvThemeChildList.setLayoutManager(new LinearLayoutManager(mContext));
        rvThemeChildList.setAdapter(mAdapter);
        stateLoading();
        mPresenter.getTagChildData(tagId);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tag;
    }

    @Override
    public void showContents(List<TagChildBean.ItemListBean> itemListBeans) {
        this.itemListBeans.addAll(itemListBeans);

    }

    @Override
    public void showMoreContents(List<TagChildBean.ItemListBean> itemListBeans) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
