package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.DailyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2017/12/22.
 */

public class DailyRecyclerAdapter extends BaseRecyclerAdapter<DailyBean.IssueListBean.ItemListBean> {


    public enum ITEM_TYPE{
        TYPE_DATE,
        TYPE_NEW
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE.TYPE_DATE.ordinal();
        }else {
            return ITEM_TYPE.TYPE_NEW.ordinal();
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public DailyRecyclerAdapter(Context context, List<DailyBean.IssueListBean.ItemListBean> datas) {
        super(context, datas);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE.TYPE_DATE.ordinal()){
            return new DateViewHolder(mLayoutInflater.inflate(R.layout.item_date,parent,false));
        }else {
            return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_daily,parent,false));
        }
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof DateViewHolder){
            String date=getDate();
            ((DateViewHolder)holder).textView.setText(date);
        }else {
            String detail=datas.get(position-1).getData().getAuthor().getName()+" / #" +datas.get(position-1).getData().getTags().get(0);
            ((ItemViewHolder)holder).mAuthorText.setText(detail);
            ((ItemViewHolder)holder).mTitleTest.setText(datas.get(position-1).getData().getTitle());
            ImageLoader.load(mContext,datas.get(position-1).getData().getAuthor().getIcon(),((ItemViewHolder)holder).mAuthorImage);
            ImageLoader.load(mContext,datas.get(position-1).getData().getCover().getFeed(),((ItemViewHolder)holder).mDailyImage);
        }
    }

    private String getDate() {
        Date dt= new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
        String dateString = simpleDateFormat.format(dt);
        return dateString;
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_date)
        TextView textView;

        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,textView);
        }
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
}
