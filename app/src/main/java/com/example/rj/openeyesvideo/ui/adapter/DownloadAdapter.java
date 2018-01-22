package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.bean.DownloadBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2018/1/22.
 */

public class DownloadAdapter extends BaseRecyclerAdapter<DownloadBean> {



    public DownloadAdapter(Context context, List<DownloadBean> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        String detail=datas.get(position).getAuthorName()+" / #" +datas.get(position).getAuthorSlogen();
        ((ItemViewHolder)holder).mAuthorText.setText(detail);
        ((ItemViewHolder)holder).mTitleTest.setText(datas.get(position).getTitle());
        ImageLoader.loadCircle(mContext,datas.get(position).getAuthorIcon(),((ItemViewHolder)holder).mAuthorImage);
        ImageLoader.load(mContext,datas.get(position).getImage(),((ItemViewHolder)holder).mDailyImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class  ItemViewHolder extends RecyclerView.ViewHolder{

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

    public void  addDownloadData(List<DownloadBean> downloadBeans){
        datas.clear();
        datas.addAll(downloadBeans);
        Log.d("hzj", "addHistoryData: datas"+datas.size());
        //datas=listBeans;
        notifyDataSetChanged();
    }
}
