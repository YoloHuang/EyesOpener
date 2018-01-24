package com.example.rj.openeyesvideo.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.ui.adapter.TopAdapter;
import com.example.rj.openeyesvideo.widget.JumpShowTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangzhijian on 2018/1/25.
 */

public class TopPageView extends FrameLayout {

    Context context;

    @BindView(R.id.vp_top)
    ViewPager viewPager;
    @BindView(R.id.iv_top_show)
    ImageView imageView;
    @BindView(R.id.tv_top_des)
    JumpShowTextView topDes;
    @BindView(R.id.tv_top_title)
    JumpShowTextView topTitle;

    TopAdapter topAdapter;
    List<ItemListBean> listBeans;

    public TopPageView(@NonNull Context context) {
        this(context,null);

    }

    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }


    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context,attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.item_top,this);
        ButterKnife.bind(this);
    }

    public void setData(List<ItemListBean> itemListBeans){
        this.listBeans=itemListBeans;
        topAdapter=new TopAdapter(context,listBeans);
        viewPager.setAdapter(topAdapter);
        setOnPageChange();
        topDes.setText(listBeans.get(0).getData().getSlogan());
        topTitle.setText(listBeans.get(0).getData().getTitle());
    }

    private void setOnPageChange() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                topDes.setText(listBeans.get(position).getData().getSlogan());
                topTitle.setText(listBeans.get(position).getData().getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void changeTopPageView(int item){
        viewPager.setCurrentItem(item);
        topDes.setText(listBeans.get(item).getData().getSlogan());
        topTitle.setText(listBeans.get(item).getData().getTitle());
    }
}
