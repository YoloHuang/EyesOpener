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
import com.example.rj.openeyesvideo.ui.view.ListEndView;
import com.example.rj.openeyesvideo.util.DiffUtilCallBack;
import com.example.rj.openeyesvideo.widget.JumpShowTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2017/12/28.
 */

public class DetailAdapter extends BaseRecyclerAdapter<ItemListBean> {

ItemListBean data;
boolean islike;
int likeCount;

    public enum ITEM_TYPE{
        TYPE_INFO,
        TYPE_TEXT,
        YTPE_VIDEO,
        TYPE_END
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE.TYPE_INFO.ordinal();
        }else if (position==1){
            return ITEM_TYPE.TYPE_TEXT.ordinal();
        }else if(position==(datas.size()+2)){
            return ITEM_TYPE.TYPE_END.ordinal();
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
        }else if(viewType==ITEM_TYPE.TYPE_END.ordinal()){
            View listEndView=new ListEndView(mContext);
            listEndView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
            return new Holder(listEndView);
        }else {
            return new RelateViewHolder(mLayoutInflater.inflate(R.layout.item_detail_video,parent,false));
        }
    }

    @Override
    public void convert(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof InfoViewHolder){
            ((InfoViewHolder)holder).detailDescribe.setText(data.getData().getDescription());
            int duration=data.getData().getDuration();
            String time=duration/60+"'"+duration%60+"''";
            ((InfoViewHolder)holder).detailTag.setText("#"+data.getData().getCategory()+" / "+time+" / "+"开眼精选");
            ((InfoViewHolder)holder).detailTitle.setText(data.getData().getTitle());
            likeCount=data.getData().getConsumption().getCollectionCount();
            if(islike){
                likeCount++;
            }
            ((InfoViewHolder)holder).likenum.setText(""+likeCount);
            ((InfoViewHolder)holder).likenum.setSelected(islike);
            ((InfoViewHolder)holder).replynum.setText(""+data.getData().getConsumption().getReplyCount());
            ((InfoViewHolder)holder).sharenum.setText(""+data.getData().getConsumption().getShareCount());
            if(data.getData().getAuthor()==null){
                ((InfoViewHolder)holder).textAuthor.setVisibility(View.GONE);
                ((InfoViewHolder)holder).textdiscribtion.setVisibility(View.GONE);
                ((InfoViewHolder)holder).imageAuthor.setVisibility(View.GONE);
            }else {
                ((InfoViewHolder)holder).textAuthor.setText(data.getData().getAuthor().getName());
                ((InfoViewHolder)holder).textdiscribtion.setText(data.getData().getAuthor().getDescription());
                ImageLoader.loadCircle(mContext,data.getData().getAuthor().getIcon(),((InfoViewHolder)holder).imageAuthor);
            }
            ((InfoViewHolder) holder).likenum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onButtonClickListener!=null){
                        onButtonClickListener.onButtonClick(v,0);
                        if(((InfoViewHolder) holder).likenum.isSelected()){
                            ((InfoViewHolder) holder).likenum.setSelected(false);
                            likeCount--;
                            ((InfoViewHolder)holder).likenum.setText(""+likeCount);
                        }else {
                            ((InfoViewHolder) holder).likenum.setSelected(true);
                            likeCount++;
                            ((InfoViewHolder)holder).likenum.setText(""+likeCount);
                        }
                    }
                }
            });
            ((InfoViewHolder)holder).sharenum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onButtonClickListener!=null){
                        onButtonClickListener.onButtonClick(v,1);
                    }
                }
            });
            ((InfoViewHolder)holder).replynum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onButtonClickListener!=null){
                        onButtonClickListener.onButtonClick(v,2);
                    }
                }
            });
            ((InfoViewHolder)holder).download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onButtonClickListener!=null){
                        onButtonClickListener.onButtonClick(v,3);
                    }
                }
            });
        }else if(holder instanceof RelateViewHolder) {

            ((RelateViewHolder)holder).relateItemName.setText(datas.get(position-2).getData().getTitle());
            String time=datas.get(position-2).getData().getDuration()/60+"'"+datas.get(position-2).getData().getDuration()%60+"''";
            ((RelateViewHolder)holder).relateITemTag.setText("#"+datas.get(position-2).getData().getCategory()+" / "+time);
            ImageLoader.loadRound (mContext,datas.get(position-2).getData().getCover().getFeed() ,((RelateViewHolder)holder).videoImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    public void getItemData(ItemListBean itemListBean){
        this.data=itemListBean;
        notifyDataSetChanged();
    }

    public void setlike(boolean islike){
        this.islike=islike;
    }

    public void getData(List<ItemListBean> itemListBeans){
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilCallBack(datas,itemListBeans),false);
        datas.clear();
        datas.addAll(itemListBeans);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return datas.size()+3;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item_author_name)
        TextView textAuthor;
        @BindView(R.id.tv_item_author_dis)
        TextView textdiscribtion;
        @BindView(R.id.iv_item_author)
        ImageView imageAuthor;
        @BindView(R.id.detail_title)
        JumpShowTextView detailTitle;
        @BindView(R.id.detail_tag)
        JumpShowTextView detailTag;
        @BindView(R.id.detail_describe)
        JumpShowTextView detailDescribe;
        @BindView(R.id.tv_likenum)
        TextView likenum;
        @BindView(R.id.tv_sharenum)
        TextView sharenum;
        @BindView(R.id.tv_reply)
        TextView replynum;
        @BindView(R.id.tv_download)
        TextView download;



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

    public static class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
