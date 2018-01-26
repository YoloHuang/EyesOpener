package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.ui.activity.DetailActivity;

import org.slf4j.ILoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rj on 2018/1/24.
 */

public class TopAdapter extends PagerAdapter {

    Context context;
    List<ItemListBean> listBeans=new ArrayList<>();

    public TopAdapter(Context context, List<ItemListBean> listBeans){
        this.context=context;
        this.listBeans=listBeans;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_top_image,container,false);
        ImageView imageView=view.findViewById(R.id.iv_top);
        ImageLoader.load(context,listBeans.get(position).getData().getCover().getFeed(),imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context, DetailActivity.class);
                ItemListBean itemListBean=listBeans.get(position);
                intent.putExtra("itemListBean",itemListBean);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

}
