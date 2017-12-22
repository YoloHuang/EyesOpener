package com.example.rj.openeyesvideo.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rj.openeyesvideo.APP.App;

/**
 * Created by rj on 2017/12/22.
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

}
