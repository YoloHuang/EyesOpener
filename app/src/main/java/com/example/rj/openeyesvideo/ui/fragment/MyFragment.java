package com.example.rj.openeyesvideo.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.SimpleFragment;
import com.example.rj.openeyesvideo.ui.activity.DownloadActivity;
import com.example.rj.openeyesvideo.ui.activity.HistoryActivity;
import com.example.rj.openeyesvideo.ui.activity.LikeActivity;
import com.example.rj.openeyesvideo.ui.adapter.BaseRecyclerAdapter;
import com.example.rj.openeyesvideo.ui.adapter.HistoryAdapter;
import com.example.rj.openeyesvideo.ui.adapter.MyRecycleradapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by rj on 2017/12/26.
 */

public class MyFragment extends SimpleFragment{

    LinearLayoutManager mLinearLayoutManager;
    MyRecycleradapter mAdapter;

    @BindView(R.id.view_main)
    RecyclerView myRecyclerView;
    List<String> list=new ArrayList<String>(5);


    @Override
    protected void initEventAndData() {
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
                        break;
                    case 5:
                        break;
                    case 6:
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
