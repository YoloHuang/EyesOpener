package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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

    public DailyRecyclerAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);

    }

    /**
     * 第一栏显示topViewPager，后面根据数据类型显示日期或者具体数据
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.TYPE_TOP.ordinal();
        } else if (datas.get(position - 1).getType().equals("textHeader")) {
            return ITEM_TYPE.TYPE_DATE.ordinal();
        }
        return ITEM_TYPE.TYPE_NEW.ordinal();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE.TYPE_DATE.ordinal()) {
            itemView = new ItemDailyDateView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_TOP.ordinal()) {
            itemView = new TopPageView(mContext);

        } else {
            itemView = new ItemDailyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(ViewHolder holder, final int position) {
        View view = holder.itemView;
        if (view instanceof ItemDailyDateView) {
            ((ItemDailyDateView) view).setData(datas.get(position - 1));
        } else if (view instanceof TopPageView) {
            topItemView = view;
            ((TopPageView) view).setData(topList);
        } else {
            ((ItemDailyView) view).setData(datas.get(position - 1));
            ((ItemDailyView) view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    /**
     * 调用DiffUtil来进行判断数据是否一致，从而在adapter中更新数据
     *
     * @param listBeans
     */
    public void addDailyData(List<ItemListBean> listBeans) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallBack(datas, listBeans), false);
        datas.clear();
        datas.addAll(listBeans);
        diffResult.dispatchUpdatesTo(this);
    }

    public void addTopData(List<ItemListBean> listBeans) {
        topList = listBeans;
    }

    /**
     * 切换TopViewPager的ImageView
     */
    public void changeTopPageView() {
        if (topItemView instanceof TopPageView) {
            ((TopPageView) topItemView).changeTopPageView();
        }
    }

    /**
     * 停止JumpShowTextView的跳转显示
     */
    public void stopText() {
        if (topItemView instanceof TopPageView) {
            ((TopPageView) topItemView).stopText();
        }
    }

    /**
     * JumpShowTextView直接显示
     */
    public void startText() {
        if (topItemView instanceof TopPageView) {
            ((TopPageView) topItemView).startText();
        }
    }

    public enum ITEM_TYPE {
        TYPE_DATE,
        TYPE_NEW,
        TYPE_TOP
    }

    public static class Holder extends ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }


}
