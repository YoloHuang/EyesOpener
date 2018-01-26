package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2018/1/26.
 */

public class SearchHotAdapter extends BaseRecyclerAdapter<String> {



    public SearchHotAdapter(Context context, List<String> datas) {
        super(context, datas);
        //this.datas=datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotTitleViewHolder(mLayoutInflater.inflate(R.layout.view_search_hot,parent,false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        ((HotTitleViewHolder)holder).hotTitle.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class HotTitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_search_hot_title)
        TextView hotTitle;

        public HotTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void getHotData( List<String> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

}
