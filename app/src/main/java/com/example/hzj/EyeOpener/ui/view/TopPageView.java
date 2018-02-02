package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    @BindView(R.id.ll_top_indicator)
    LinearLayout indicators;

    TopAdapter topAdapter;
    List<ItemListBean> listBeans;

    int newPosition;

    public TopPageView(@NonNull Context context) {
        this(context, null);

    }

    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }


    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {

        View.inflate(context, R.layout.item_top, this);
        newPosition = 1;
        ButterKnife.bind(this);
    }

    public void setData(List<ItemListBean> itemListBeans) {
        this.listBeans = itemListBeans;
        topAdapter = new TopAdapter(context, listBeans);
        viewPager.setAdapter(topAdapter);
        setOnPageChange();
        addIndicator(listBeans.size());
        topDes.setText(listBeans.get(0).getData().getSlogan());
        topTitle.setText(listBeans.get(0).getData().getTitle());
    }

    private void addIndicator(int size) {
        indicators.removeAllViews();
        for (int i = 0; i < size; i++) {
            Indicator indicator = new Indicator(context);
            indicators.addView(indicator);
            if (i == 0) indicator.setImageView(true);
        }
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
                newPosition = position + 1;
                topDes.setText(listBeans.get(position).getData().getSlogan());
                topTitle.setText(listBeans.get(position).getData().getTitle());
                for (int j = 0; j < listBeans.size(); j++) {
                    if (j == position) {
                        ((Indicator) indicators.getChildAt(j)).setImageView(true);
                    } else {
                        ((Indicator) indicators.getChildAt(j)).setImageView(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void stopText() {
        topDes.stopText();
        topTitle.stopText();
    }

    public void startText() {
        topDes.startText();
        topTitle.startText();
    }

    public void changeTopPageView() {
        if (newPosition < listBeans.size()) {
            viewPager.setCurrentItem(newPosition);
        } else {
            newPosition = 0;
            viewPager.setCurrentItem(newPosition);
        }
    }


}
