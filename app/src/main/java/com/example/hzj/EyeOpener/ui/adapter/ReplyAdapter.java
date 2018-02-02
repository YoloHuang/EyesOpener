package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hzj.EyeOpener.model.bean.ReplyBean;
import com.example.hzj.EyeOpener.ui.view.ItemReplyTitleView;
import com.example.hzj.EyeOpener.ui.view.ItemReplyView;
import com.example.hzj.EyeOpener.ui.view.ListEndView;

import java.util.List;

/**
 * Created by hzj on 2018/1/11.
 */

public class ReplyAdapter extends BaseRecyclerAdapter<ReplyBean.ItemListBean> {


    public ReplyAdapter(Context context, List<ReplyBean.ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return ITEM_TYPE.TYPE_END.ordinal();
        }
        switch (datas.get(position).getType()) {
            case "reply":
                return ITEM_TYPE.TYPE_REPLY.ordinal();

            case "leftAlignTextHeader":
                return ITEM_TYPE.TYPE_TITLE.ordinal();

            default:
                return 0;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE.TYPE_END.ordinal()) {
            itemView = new ListEndView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_TITLE.ordinal()) {
            itemView = new ItemReplyTitleView(mContext);
        } else {
            itemView = new ItemReplyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(itemView);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        if (itemView instanceof ItemReplyView) {
            ((ItemReplyView) itemView).setData(datas.get(position));
        } else if (itemView instanceof ItemReplyTitleView) {
            ((ItemReplyTitleView) itemView).setData(datas.get(position));
        } else if (itemView instanceof ListEndView) {
            ((ListEndView) itemView).textEnd.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public void getData(ReplyBean replyBean) {
        for (ReplyBean.ItemListBean itemListBean : replyBean.getItemList()) {
            if (itemListBean.getType().equals("videoSmallCard")) {
                replyBean.getItemList().remove(itemListBean);
            }
        }
        datas = replyBean.getItemList();
    }

    public void getMoreData(ReplyBean replyBean) {
        for (ReplyBean.ItemListBean itemListBean : replyBean.getItemList()) {
            if (itemListBean.getType().equals("videoSmallCard")) {
                replyBean.getItemList().remove(itemListBean);
            }
        }
        datas.addAll(replyBean.getItemList());
        notifyDataSetChanged();
    }

    public enum ITEM_TYPE {
        TYPE_TITLE,
        TYPE_END,
        TYPE_REPLY,
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
