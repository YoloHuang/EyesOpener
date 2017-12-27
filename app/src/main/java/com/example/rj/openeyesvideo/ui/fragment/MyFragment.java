package com.example.rj.openeyesvideo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.SimpleFragment;
import com.example.rj.openeyesvideo.ui.adapter.BaseRecyclerAdapter;
import com.example.rj.openeyesvideo.ui.adapter.MyRecycleradapter;

import java.util.ArrayList;
import java.util.List;

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
        list.add("");
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

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listcomment;
    }
}
