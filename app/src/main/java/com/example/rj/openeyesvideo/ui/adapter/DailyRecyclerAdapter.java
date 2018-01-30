package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.ui.view.ItemDailyDateView;
import com.example.rj.openeyesvideo.ui.view.ItemDailyView;
import com.example.rj.openeyesvideo.ui.view.TopPageView;
import com.example.rj.openeyesvideo.util.DiffUtilCallBack;
import com.example.rj.openeyesvideo.widget.JumpShowTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2017/12/22.
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





}
