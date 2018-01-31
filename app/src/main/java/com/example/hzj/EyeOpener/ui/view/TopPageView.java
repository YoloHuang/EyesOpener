package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.adapter.TopAdapter;
import com.example.hzj.EyeOpener.widget.JumpShowTextView;

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
//    LinearLayout linearLayout;
//    LinearLayout indicators;

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
//       topDes.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        newView();
//        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,810));
//
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.gravity= Gravity.BOTTOM ;
//        linearLayout.setLayoutParams(layoutParams);
//        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//        imageView.setImageResource(R.mipmap.home_page_header_icon);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,110));
//        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//
//        topTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        topTitle.

    }
//
//    private void newView() {
//        linearLayout=new LinearLayout(context);
//        imageView=new ImageView(context);
//        viewPager=new ViewPager(context);
//        topDes=new JumpShowTextView(context);
//        topTitle=new JumpShowTextView(context);
//        indicators=new LinearLayout(context);
//    }

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
                topDes.setWithAnimation(true);
                topTitle.setWithAnimation(true);
                topDes.setText(listBeans.get(position).getData().getSlogan());
                topTitle.setText(listBeans.get(position).getData().getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void stopText(){
        topDes.stopText();
        topTitle.stopText();
    }

    public void changeTopPageView(int position){
        viewPager.setCurrentItem(position);
    }


}
