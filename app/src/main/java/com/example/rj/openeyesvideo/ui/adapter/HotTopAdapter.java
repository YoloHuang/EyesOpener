package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.util.DiffUtilCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2017/12/26.
 */

public class HotTopAdapter extends BaseRecyclerAdapter<ItemListBean> {


    public HotTopAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        String detail=datas.get(position).getData().getAuthor().getName()+" / #" +datas.get(position).getData().getCategory();
        ((ItemViewHolder)holder).mAuthorText.setText(detail);
        ((ItemViewHolder)holder).mTitleTest.setText(datas.get(position).getData().getTitle());
        ImageLoader.load(mContext,datas.get(position).getData().getAuthor().getIcon(),((ItemViewHolder)holder).mAuthorImage);
        ImageLoader.load(mContext,datas.get(position).getData().getCover().getFeed(),((ItemViewHolder)holder).mDailyImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);

                }
            }
        });
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_daily_item_image)
        ImageView mDailyImage;
        @BindView(R.id.iv_daily_author)
        ImageView mAuthorImage;
        @BindView(R.id.item_text_author)
        TextView mAuthorText;
        @BindView(R.id.item_text_title)
        TextView mTitleTest;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void  addHotTopData(List<ItemListBean> itemListBeans){
        datas.clear();
        datas.addAll(itemListBeans);
        //datas=listBeans;
        notifyDataSetChanged();
    }
}
