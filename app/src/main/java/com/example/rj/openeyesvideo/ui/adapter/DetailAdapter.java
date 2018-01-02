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
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.ui.activity.DetailActivity;
import com.example.rj.openeyesvideo.util.DiffUtilCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2017/12/28.
 */

public class DetailAdapter extends BaseRecyclerAdapter<ItemListBean> {

ItemListBean data;

    public enum ITEM_TYPE{
        TYPE_INFO,
        TYPE_TEXT,
        YTPE_VIDEO
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE.TYPE_INFO.ordinal();
        }else if (position==1){
            return ITEM_TYPE.TYPE_TEXT.ordinal();
        }else {
            return ITEM_TYPE.YTPE_VIDEO.ordinal();
        }
    }

    public DetailAdapter(Context context, List<ItemListBean> datas){
        super(context,datas);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE.TYPE_INFO.ordinal()){
            return new InfoViewHolder(mLayoutInflater.inflate(R.layout.item_detail_info,parent,false));
        }else if(viewType==ITEM_TYPE.TYPE_TEXT.ordinal()){
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_detail_text,parent,false));
        }else {
            return new RelateViewHolder(mLayoutInflater.inflate(R.layout.item_detail_video,parent,false));
        }
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InfoViewHolder){
            ((InfoViewHolder)holder).detailDescribe.setText(data.getData().getDescription());
            ((InfoViewHolder)holder).detailTag.setText(data.getData().getCategory());
            ((InfoViewHolder)holder).detailTitle.setText(data.getData().getTitle());
            ((InfoViewHolder)holder).likenum.setText(data.getData().getConsumption().getCollectionCount());
            ((InfoViewHolder)holder).replynum.setText(data.getData().getConsumption().getReplyCount());
            ((InfoViewHolder)holder).sharenum.setText(data.getData().getConsumption().getShareCount());
            ((InfoViewHolder)holder).textAuthor.setText(data.getData().getAuthor().getName());
            ((InfoViewHolder)holder).textdiscribtion.setText(data.getData().getAuthor().getDescription());
            ImageLoader.load(mContext,data.getData().getAuthor().getIcon(),((InfoViewHolder)holder).imageAuthor);
        }else if(holder instanceof RelateViewHolder) {
            ((RelateViewHolder)holder).relateItemName.setText(datas.get(position-2).getData().getTitle());
            ((RelateViewHolder)holder).relateITemTag.setText(datas.get(position-2).getData().getCategory());
            ImageLoader.load (mContext,datas.get(position-2).getData().getCover().getFeed() ,((RelateViewHolder)holder).videoImage);
        }
    }

    public void getItemData(ItemListBean itemListBean){
        this.data=itemListBean;
    }

    public void getData(List<ItemListBean> itemListBeans){
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilCallBack(datas,itemListBeans),false);
        datas.clear();
        datas.addAll(itemListBeans);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return datas.size()+2;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item_author_name)
        TextView textAuthor;
        @BindView(R.id.tv_item_author_dis)
        TextView textdiscribtion;
        @BindView(R.id.iv_item_author)
        ImageView imageAuthor;
        @BindView(R.id.detail_title)
        TextView detailTitle;
        @BindView(R.id.detail_tag)
        TextView detailTag;
        @BindView(R.id.detail_describe)
        TextView detailDescribe;
        @BindView(R.id.tv_likenum)
        TextView likenum;
        @BindView(R.id.tv_sharenum)
        TextView sharenum;
        @BindView(R.id.tv_reply)
        TextView replynum;



        public InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.relate_title)
        TextView relateTitle;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class RelateViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_video_image)
        ImageView videoImage;
        @BindView(R.id.item_video_name)
        TextView relateItemName;
        @BindView(R.id.item_video_tag)
        TextView relateITemTag;

        public RelateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
