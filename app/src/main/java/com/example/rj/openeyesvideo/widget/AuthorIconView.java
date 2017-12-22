package com.example.rj.openeyesvideo.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by rj on 2017/12/22.
 */

public class AuthorIconView extends android.support.v7.widget.AppCompatImageView {

    Paint paint;

    public AuthorIconView(Context context) {
        super(context);
    }

    public AuthorIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AuthorIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
    }
}
