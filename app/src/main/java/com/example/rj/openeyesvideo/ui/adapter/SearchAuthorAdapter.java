package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;
import com.example.rj.openeyesvideo.ui.activity.DetailActivity;
import com.example.rj.openeyesvideo.ui.activity.SearchActivity;
import com.example.rj.openeyesvideo.ui.view.SearchAuthorItemView;

import java.util.List;

/**
 * Created by rj on 2018/1/29.
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
