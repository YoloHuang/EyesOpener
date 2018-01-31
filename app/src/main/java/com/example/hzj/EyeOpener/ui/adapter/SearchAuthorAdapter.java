package com.example.hzj.EyeOpener.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.example.hzj.EyeOpener.ui.view.SearchAuthorItemView;

/**
 * Created by hzj on 2018/1/29.
 */

public class SearchAuthorAdapter extends PagerAdapter {

    Context context;
    ItemListBean itemListBean;
    private LayoutInflater inflater;

    public SearchAuthorAdapter(Context context, ItemListBean list){
        this.context=context;
        this.itemListBean=list;
    }

    @Override
    public int getCount() {
        return itemListBean.getData().getListBeans().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        SearchAuthorItemView view=new SearchAuthorItemView(context);
        view.setData(itemListBean.getData().getListBeans().get(position));
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(context, DetailActivity.class);
                intent.putExtra("itemListBean",itemListBean.getData().getListBeans().get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
