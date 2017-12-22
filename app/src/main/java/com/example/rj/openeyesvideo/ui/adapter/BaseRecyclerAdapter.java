package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by rj on 2017/12/22.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {


    protected List<T> datas;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;


    public BaseRecyclerAdapter(Context context, List<T> datas){
        mContext=context;
        this.datas=datas;
        this.mLayoutInflater=LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert(holder, position);
    }

    public abstract void convert(RecyclerView.ViewHolder holder,int position);


}
