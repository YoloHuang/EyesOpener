package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.LikeBean;
import com.example.hzj.EyeOpener.ui.view.ListEndView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/9.
 */

public class LikeAdapter extends BaseRecyclerAdapter<LikeBean> {

    public LikeAdapter(Context context, List<LikeBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return ITEM_TYPE.TYPE_END.ordinal();
        } else {
            return ITEM_TYPE.TYPE_NEW.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.TYPE_NEW.ordinal()) {
            return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_daily, parent, false));
        } else {
            View view = new ListEndView(mContext);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new Holder(view);
        }

    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            String detail = datas.get(position).getAuthorName() + " / #" + datas.get(position).getAuthorSlogen();
            ((ItemViewHolder) holder).mAuthorText.setText(detail);
            ((ItemViewHolder) holder).mTitleTest.setText(datas.get(position).getTitle());
            ImageLoader.loadCircle(mContext, datas.get(position).getAuthorIcon(), ((ItemViewHolder) holder).mAuthorImage);
            ImageLoader.load(mContext, datas.get(position).getImage(), ((ItemViewHolder) holder).mDailyImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public void addLikeData(List<LikeBean> LikeBean) {
        datas.clear();
        datas.addAll(LikeBean);
        notifyDataSetChanged();
    }

    public enum ITEM_TYPE {
        TYPE_NEW,
        TYPE_END
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

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
            ButterKnife.bind(this, itemView);
        }
    }

}
