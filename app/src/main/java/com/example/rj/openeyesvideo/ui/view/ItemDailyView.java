package com.example.rj.openeyesvideo.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rj.openeyesvideo.R;

/**
 * Created by rj on 2018/1/11.
 */

public class ItemDailyView extends LinearLayout {

    Context context;

    public ItemDailyView(Context context) {
        this(context,null);
    }

    public ItemDailyView(Context context, @Nullable AttributeSet attrs) {
        this(context,null,0);
    }

    public ItemDailyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }
    private void initView(){
        View.inflate(context, R.layout.item_daily,this);

    }
}
