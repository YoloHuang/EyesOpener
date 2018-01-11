package com.example.rj.openeyesvideo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.model.bean.ReplyBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rj on 2018/1/11.
 */

public class ItemReplyTitleView extends RelativeLayout {
    Context context;
    @BindView(R.id.tv_replytitle)
    TextView replyTitle;

    public ItemReplyTitleView(Context context) {
        super(context);
        new ItemReplyTitleView(context,null);
    }

    public ItemReplyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        new ItemReplyTitleView(context,null,0);
    }

    public ItemReplyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();

    }

    private void initView() {
        View.inflate(context, R.layout.item_replytitle,this);
        ButterKnife.bind(this);
    }

    public void setData(ReplyBean.ItemListBean itemListBean){
        if(itemListBean.getType()=="leftAlignTextHeader"){
            replyTitle.setText(itemListBean.getData().getText());
        }
    }
}
