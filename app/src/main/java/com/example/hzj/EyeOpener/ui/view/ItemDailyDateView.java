package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/30.
 */

public class ItemDailyDateView extends RelativeLayout {

    @BindView(R.id.text_date)
    TextView textView;
    Context context;

    public ItemDailyDateView(Context context) {
        this(context,null);
    }

    public ItemDailyDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public ItemDailyDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context,attrs);
    }


    private void initView() {
        View.inflate(context, R.layout.item_date,this);
        ButterKnife.bind(this);
    }

    public void setData(ItemListBean itemListBean){
        textView.setText(itemListBean.getData().getText());
    }
}
