package com.example.rj.openeyesvideo.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.rj.openeyesvideo.R;

/**
 * Created by rj on 2018/1/11.
 */

public class ListEndView extends FrameLayout {

    Context context;

    public ListEndView(@NonNull Context context) {
        super(context);
        new ListEndView(context,null);
    }

    public ListEndView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        new  ListEndView(context,null,0);
    }

    public ListEndView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.item_listend,this);

    }
}
