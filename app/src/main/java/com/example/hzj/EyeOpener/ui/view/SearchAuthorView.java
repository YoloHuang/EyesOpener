package com.example.hzj.EyeOpener.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.adapter.SearchAuthorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/1/29.
 */

public class SearchAuthorView extends LinearLayout {

    Context context;
    @BindView(R.id.iv_item_author)
    ImageView authorIcon;
    @BindView(R.id.tv_item_author_name)
    TextView authorName;
    @BindView(R.id.tv_item_author_dis)
    TextView authorDis;
    @BindView(R.id.vp_search_author)
    ViewPager viewPager;
    ItemListBean resultBean;
    SearchAuthorAdapter adapter;

    public SearchAuthorView(Context context) {
        this(context, null);
    }

    public SearchAuthorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SearchAuthorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }


    private void initView() {
        View.inflate(context, R.layout.item_search_author, this);
        ButterKnife.bind(this);
    }

    public void getData(ItemListBean resultBean) {
        this.resultBean = resultBean;
        ImageLoader.loadCircle(context, resultBean.getData().getHeader().getIcon(), authorIcon);
        authorDis.setText(resultBean.getData().getHeader().getDescription());
        authorName.setText(resultBean.getData().getHeader().getTitle());
        adapter = new SearchAuthorAdapter(context, resultBean);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(8);
    }

}
