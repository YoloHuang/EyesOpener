package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2017/12/27.
 */

public class MyRecycleradapter extends BaseRecyclerAdapter<String> {

    public MyRecycleradapter(Context context, List<String> datas) {
        super(context, datas);
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.TYPE_IMAGE.ordinal();
        } else {
            return ITEM_TYPE.TYPE_TEXT.ordinal();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_myimage, parent, false));
        } else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_mytext, parent, false));
        }
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).textView.setText(datas.get(position - 1));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        } else {
            ((ImageViewHolder) holder).imageView.setImageResource(R.mipmap.iv_top);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public enum ITEM_TYPE {
        TYPE_IMAGE,
        TYPE_TEXT
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_my)
        TextView textView;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_my)
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
