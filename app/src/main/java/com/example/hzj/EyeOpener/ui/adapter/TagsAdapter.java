package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.TagsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2017/12/26.
 */

public class TagsAdapter extends BaseRecyclerAdapter<TagsBean> {


    public TagsAdapter(Context context, List<TagsBean> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_tag, parent, false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        ((ItemViewHolder) holder).textTag.getPaint().setShadowLayer(5, 0, 0, Color.BLACK);
        ((ItemViewHolder) holder).textTag.setText(datas.get(position).getName());
        ImageLoader.load(mContext, datas.get(position).getBgPicture(), ((ItemViewHolder) holder).tagImageIiew);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addTagsData(List<TagsBean> tagsBean) {
        datas.clear();
        datas.addAll(tagsBean);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tag_bg)
        ImageView tagImageIiew;
        @BindView(R.id.tag_name)
        TextView textTag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
