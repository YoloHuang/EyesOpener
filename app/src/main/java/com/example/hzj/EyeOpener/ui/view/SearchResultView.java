package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.example.hzj.EyeOpener.ui.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/30.
 */

public class SearchResultView extends RelativeLayout {

    @BindView(R.id.rv_search_result)
    public RecyclerView recyclerView;

    SearchResultAdapter adapter;
    List<ItemListBean> itemListBeans=new ArrayList<>();
    public LinearLayoutManager manager;

    Context context;
    public SearchResultView(Context context) {
        this(context,null);
    }

    public SearchResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }
    public SearchResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }


    private void initView() {
        View.inflate(context, R.layout.item_search_result,this);
        ButterKnife.bind(this);
        adapter=new SearchResultAdapter(context,itemListBeans);
        manager=new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent=new Intent();
                intent.setClass(context, DetailActivity.class);
                ItemListBean itemListBean=itemListBeans.get(id);
                intent.putExtra("itemListBean",itemListBean);
                context.startActivity(intent);
            }
        });
    }

    public void setdata(List<ItemListBean> itemListBeans){
        this.itemListBeans=itemListBeans;
        adapter.setData(itemListBeans);
    }
    public void setMoreData(List<ItemListBean> itemListBeans){
        this.itemListBeans.addAll(itemListBeans);
        adapter.setData(this.itemListBeans);
    }
}
