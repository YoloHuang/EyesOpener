package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/30.
 */

public class SearchAuthorItemView extends CardView {

    @BindView(R.id.iv_daily_item_image)
    ImageView itemImage;
    @BindView(R.id.item_text_title)
    TextView textTitle;
    @BindView(R.id.item_text_tag)
            TextView textTag;

    Context context;

    public SearchAuthorItemView(Context context) {
       this(context,null);
    }

    public SearchAuthorItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }



    public SearchAuthorItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context,attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.item_search_authoritem,this);
        ButterKnife.bind(this);
    }

    public void  setData(ItemListBean itemListBean){
        textTag.setText("# "+itemListBean.getData().getCategory());
        textTitle.setText(itemListBean.getData().getTitle());
        ImageLoader.load(context,itemListBean.getData().getCover().getFeed(),itemImage);
    }
}
