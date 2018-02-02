package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2017/12/26.
 */

public class HotTopAdapter extends BaseRecyclerAdapter<ItemListBean> {


    public HotTopAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        String detail = datas.get(position).getData().getAuthor().getName() + " / #" + datas.get(position).getData().getCategory();
        int duration = datas.get(position).getData().getDuration();
        String time = duration / 60 + ":" + duration % 60;
        ((ItemViewHolder) holder).mHotTag.setText(detail);
        ((ItemViewHolder) holder).mHotTitle.setText(datas.get(position).getData().getTitle());
        ((ItemViewHolder) holder).mHotTime.setText(time);
        ImageLoader.load(mContext, datas.get(position).getData().getCover().getFeed(), ((ItemViewHolder) holder).mHotImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addHotTopData(List<ItemListBean> itemListBeans) {
        datas.clear();
        datas.addAll(itemListBeans);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_hot_item)
        ImageView mHotImage;
        @BindView(R.id.tv_hot_title)
        TextView mHotTitle;
        @BindView(R.id.tv_hot_tag)
        TextView mHotTag;
        @BindView(R.id.tv_hot_time)
        TextView mHotTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
