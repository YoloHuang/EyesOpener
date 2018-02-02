package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hzj.EyeOpener.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2018/2/2.
 * Viewpager的indicator
 */

public class Indicator extends RelativeLayout {

    @BindView(R.id.iv_indicator)
    ImageView imageView;

    Context context;

    public Indicator(Context context) {
        this(context, null);
    }

    public Indicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public Indicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.view_indicator, this);
        ButterKnife.bind(this);
    }

    public void setImageView(boolean select) {
        if (select) {
            imageView.setImageResource(R.mipmap.ic_page_indicator_focused);
        } else {
            imageView.setImageResource(R.mipmap.ic_page_indicator);
        }
    }


}
