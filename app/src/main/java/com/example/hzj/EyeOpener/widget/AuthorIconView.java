package com.example.hzj.EyeOpener.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by hzj on 2017/12/22.
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
