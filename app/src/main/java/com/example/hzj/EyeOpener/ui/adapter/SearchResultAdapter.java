package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.view.ItemDailyView;
import com.example.hzj.EyeOpener.ui.view.ListEndView;
import com.example.hzj.EyeOpener.ui.view.SearchAuthorView;

import java.util.List;

/**
 * Created by hzj on 2018/1/30.
 */

public class SearchResultAdapter extends BaseRecyclerAdapter<ItemListBean> {


    public enum ITEM_TYPE{
        TYPE_AUTHOR,
        TYPE_NEW,
        TYPE_END
    }

    @Override
    public int getItemViewType(int position) {
        if(position==datas.size()){
            return ITEM_TYPE.TYPE_END.ordinal();
        }else if(datas.get(position).getType().equals("videoCollectionWithBrief")){
            return ITEM_TYPE.TYPE_AUTHOR.ordinal();
        }else {
            return ITEM_TYPE.TYPE_NEW.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType==ITEM_TYPE.TYPE_AUTHOR.ordinal()){
            itemView=new SearchAuthorView(mContext);
        }else if(viewType==ITEM_TYPE.TYPE_END.ordinal()){
            itemView=new ListEndView(mContext);
        }else {
            itemView=new ItemDailyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    public SearchResultAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        View view=holder.itemView;
        if(view instanceof SearchAuthorView){
            ((SearchAuthorView)view).getData(datas.get(position));
        }else if(view instanceof ItemDailyView){
            ((ItemDailyView)view).setData(datas.get(position));
            ((ItemDailyView) view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }


    public void setData(List<ItemListBean> itemListBeans){
        Log.d("hzj", "setData: "+datas.size());
        //DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilCallBack(datas,itemListBeans),false);
        datas.clear();
        datas.addAll(itemListBeans);
        //diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }
}
