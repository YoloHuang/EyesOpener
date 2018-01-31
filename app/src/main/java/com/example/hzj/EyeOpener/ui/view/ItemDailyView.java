package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/11.
 */

public class ItemDailyView extends LinearLayout {

    @BindView(R.id.iv_daily_item_image)
    ImageView mDailyImage;
    @BindView(R.id.iv_daily_author)
    ImageView mAuthorImage;
    @BindView(R.id.item_text_author)
    TextView mAuthorText;
    @BindView(R.id.item_text_title)
    TextView mTitleTest;

    Context mContext;
    ItemListBean itemListBean;

    public ItemDailyView(Context context) {
        this(context,null);
    }

    public ItemDailyView(Context context, @Nullable AttributeSet attrs) {
        this(context,null,0);
    }

    public ItemDailyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initView();
    }
    private void initView(){
        View.inflate(mContext, R.layout.item_daily,this);
        ButterKnife.bind(this);
    }

    public void setData(ItemListBean itemListBean){
        this.itemListBean=itemListBean;
        String detail;
        if(itemListBean.getData().getAuthor()==null){
            detail="开眼精选 / # "+itemListBean.getData().getCategory();
        }else {
            detail=itemListBean.getData().getAuthor().getName()+" / #" +itemListBean.getData().getCategory();
            ImageLoader.loadCircle(mContext,itemListBean.getData().getAuthor().getIcon(),mAuthorImage);
        }
        mAuthorText.setText(detail);
        mTitleTest.setText(itemListBean.getData().getTitle());
        ImageLoader.load(mContext,itemListBean.getData().getCover().getFeed(),mDailyImage);
    }
    public void setclick(){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, DetailActivity.class);
                intent.putExtra("itemListBean",itemListBean);
                mContext.startActivity(intent);
            }
        });
    }
}
