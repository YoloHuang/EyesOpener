package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.view.ItemDailyDateView;
import com.example.hzj.EyeOpener.ui.view.ItemDailyView;
import com.example.hzj.EyeOpener.ui.view.TopPageView;
import com.example.hzj.EyeOpener.util.DiffUtilCallBack;

import java.util.List;

/**
 * Created by hzj on 2017/12/22.
 */

public class DailyRecyclerAdapter extends BaseRecyclerAdapter<ItemListBean> {



    List<ItemListBean> topList;
    View topItemView;


    public enum ITEM_TYPE{
        TYPE_DATE,
        TYPE_NEW,
        TYPE_TOP
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE.TYPE_TOP.ordinal();
        }else if(datas.get(position-1).getType().equals("textHeader")){
            return ITEM_TYPE.TYPE_DATE.ordinal();
        }
        return ITEM_TYPE.TYPE_NEW.ordinal();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public DailyRecyclerAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType==ITEM_TYPE.TYPE_DATE.ordinal()){
            itemView=new ItemDailyDateView(mContext);
        }else if(viewType==ITEM_TYPE.TYPE_TOP.ordinal()){
           itemView=new TopPageView(mContext);

        }else {
            itemView=new ItemDailyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(ViewHolder holder, final int position) {
        View view=holder.itemView;
        if(view instanceof ItemDailyDateView){
            ((ItemDailyDateView) view).setData(datas.get(position-1));
        }else if(view instanceof TopPageView){
            topItemView=view;
            ((TopPageView) view).setData(topList);
        } else {
            ((ItemDailyView)view).setData(datas.get(position-1));
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


    public static class Holder extends ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public void addDailyData(List<ItemListBean> listBeans){
        Log.d("hzj", "addDailyData: "+datas.size());
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilCallBack(datas,listBeans),false);
        datas.clear();
        datas.addAll(listBeans);
        //datas=listBeans;
        diffResult.dispatchUpdatesTo(this);
    }

    public void addTopData(List<ItemListBean> listBeans){
        topList=listBeans;
    }


    public void changeTopPageView(int item){
        Log.d("hzj", "changeTopPageView: item==="+item);
        if(topItemView instanceof TopPageView){
            ((TopPageView) topItemView).changeTopPageView(item);
        }
    }

    public void stopText(){
        if(topItemView instanceof TopPageView){
            ((TopPageView) topItemView).stopText();
        }
    }





}
