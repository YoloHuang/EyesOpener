package com.example.rj.openeyesvideo.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;

import butterknife.BindView;

/**
 * Created by rj on 2018/1/11.
 */

public class ListEndView extends FrameLayout {

    @BindView(R.id.text_end)
    public TextView textEnd;
    Context context;

    public ListEndView(@NonNull Context context) {
        this(context,null);
    }

    public ListEndView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,null,0);
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
