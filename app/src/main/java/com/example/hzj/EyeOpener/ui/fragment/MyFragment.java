package com.example.hzj.EyeOpener.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.SimpleFragment;
import com.example.hzj.EyeOpener.ui.activity.AboutActivity;
import com.example.hzj.EyeOpener.ui.activity.DownloadActivity;
import com.example.hzj.EyeOpener.ui.activity.HistoryActivity;
import com.example.hzj.EyeOpener.ui.activity.LikeActivity;
import com.example.hzj.EyeOpener.ui.activity.SettingActivity;
import com.example.hzj.EyeOpener.ui.adapter.MyRecycleradapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/26.
 */

public class MyFragment extends SimpleFragment{

    LinearLayoutManager mLinearLayoutManager;
    MyRecycleradapter mAdapter;

    @BindView(R.id.view_main)
    RecyclerView myRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    List<String> list=new ArrayList<String>(5);


    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("我");
        toolbarSearch.setVisibility(View.GONE);
        list.clear();
        list.add("观看记录");
        list.add("我的喜欢");
        list.add("我的缓存");
        list.add("设置");
        list.add("关于");
        mAdapter=new MyRecycleradapter(mContext,list);
        mLinearLayoutManager=new LinearLayoutManager(mContext);
        myRecyclerView.setLayoutManager(mLinearLayoutManager);
        myRecyclerView.setAdapter(mAdapter);
        initOnclickListener();
    }

    private void initOnclickListener() {
        mAdapter.setOnItemClickListener(new MyRecycleradapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                switch (id){
                    case 0:
                        break;
                    case 1:
                        Intent intent=new Intent();
                        intent.setClass(mContext, HistoryActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        Intent intent2=new Intent();
                        intent2.setClass(mContext, LikeActivity.class);
                        mContext.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent();
                        intent3.setClass(mContext, DownloadActivity.class);
                        mContext.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent();
                        intent4.setClass(mContext, SettingActivity.class);
                        mContext.startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent();
                        intent5.setClass(mContext, AboutActivity.class);
                        mContext.startActivity(intent5);
                        break;

                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listcomment;
    }
}
