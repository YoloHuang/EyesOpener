package com.example.hzj.EyeOpener.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.TagContract;
import com.example.hzj.EyeOpener.base.RootFragment;
import com.example.hzj.EyeOpener.model.bean.TagsBean;
import com.example.hzj.EyeOpener.presenter.TagsPresenter;
import com.example.hzj.EyeOpener.ui.activity.TagActivity;
import com.example.hzj.EyeOpener.ui.adapter.TagsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/26.
 */

public class TagsFragment extends RootFragment<TagsPresenter> implements TagContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    private List<TagsBean> itemListBeans = new ArrayList<>();

    GridLayoutManager mLayoutManager;

    TagsAdapter mAdapter;



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        toolbarTitle.setText("分类");
        toolbarSearch.setVisibility(View.GONE);
        stateLoading();
    }

    private void initRecyclerView() {
        mLayoutManager=new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new TagsAdapter(mContext,itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getTagsDatas();
        mAdapter.setOnItemClickListener(new TagsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent=new Intent();
                intent.setClass(mContext, TagActivity.class);
                intent.putExtra("TagId",itemListBeans.get(id).getId());
                intent.putExtra("TagName",itemListBeans.get(id).getName());
                intent.putExtra("TagImage",itemListBeans.get(id).getBgPicture());
                intent.putExtra("TagSlogen",itemListBeans.get(id).getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listcomment;
    }


    @Override
    public void showContents(List<TagsBean> tagsBean) {
        itemListBeans.addAll(tagsBean);
        Log.d("hzj", "showContents: itemListBeans"+itemListBeans.size());
        mAdapter.addTagsData(itemListBeans);
        stateStart();
    }
}
