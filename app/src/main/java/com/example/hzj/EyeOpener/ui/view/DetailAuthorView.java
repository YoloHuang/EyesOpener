package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/2.
 * Author View，用于搜索结果中
 */

public class DetailAuthorView extends RelativeLayout {


    @BindView(R.id.tv_item_author_dis)
    TextView authorDis;
    @BindView(R.id.tv_item_author_name)
    TextView authorName;
    @BindView(R.id.iv_item_author)
    ImageView authorIcon;
    Context context;


    public DetailAuthorView(Context context) {
        this(context, null);
    }

    public DetailAuthorView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public DetailAuthorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }


    private void initView() {
        View.inflate(context, R.layout.item_detail_author, this);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {

    }
}
