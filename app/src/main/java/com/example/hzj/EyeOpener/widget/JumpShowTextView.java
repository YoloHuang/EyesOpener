package com.example.hzj.EyeOpener.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by hzj on 2018/1/23.
 * 逐字显示，原理是，用一个不可见的TextView控制View的大小，用rxjava的interval实现逐字显示
 */

public class JumpShowTextView extends FrameLayout {

    public Disposable disposable;
    ArrayList<String> content;
    String text;
    TextView placeHolder;
    TextView realTextView;
    boolean isBold;
    boolean isSinglen;
    int textColor;
    float textSize;
    Context context;
    TypedArray array;
    long time;
    boolean withAnimation = true;
    String finalReal;
    int count = 0;
    boolean isRun = false;


    public JumpShowTextView(@NonNull Context context) {
        this(context, null);
    }

    public JumpShowTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        array = context.obtainStyledAttributes(attrs, R.styleable.JumpShowTextView);
        textSize = array.getDimension(R.styleable.JumpShowTextView_textSize, 20);
        textColor = array.getColor(R.styleable.JumpShowTextView_textColor, Color.WHITE);
        isBold = array.getBoolean(R.styleable.JumpShowTextView_IsBold, false);
        isSinglen = array.getBoolean(R.styleable.JumpShowTextView_IsSinglen, false);
        createText();
        initView();
    }

    public JumpShowTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public void setWithAnimation(boolean withAnimation) {
        this.withAnimation = withAnimation;
    }

    public void setText(String text) {
        this.text = text;
        placeHolder.setText(text);
        placeHolder.setVisibility(INVISIBLE);
        startView();
    }

    private void createText() {

        placeHolder = new TextView(context);
        placeHolder.getPaint().setTextSize(textSize);
        placeHolder.getPaint().setShadowLayer(5, 0, 0, Color.BLACK);
        placeHolder.setSingleLine(isSinglen);
        placeHolder.setTextColor(textColor);
        placeHolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        placeHolder.setVisibility(INVISIBLE);

        realTextView = new TextView(context);
        realTextView.getPaint().setTextSize(textSize);
        realTextView.getPaint().setShadowLayer(5, 0, 0, Color.BLACK);
        realTextView.setSingleLine(isSinglen);
        realTextView.setTextColor(textColor);
        realTextView.getPaint().setFakeBoldText(isBold);
        realTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    private void initView() {
        removeAllViews();
        addView(placeHolder);
        addView(realTextView);
    }

    private void startView() {
        count = 0;
        time = 1000 / text.length();
        if (withAnimation) {
            if (isRun) {
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                    isRun = false;
                    realTextView.setText(text);
                }
            } else {
                isRun = true;
                disposable = Flowable.interval(time, TimeUnit.MILLISECONDS)
                        .take(text.length())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                count++;
                                if (count < text.length()) {
                                    finalReal = text.substring(0, count);
                                } else {
                                    finalReal = text;
                                    isRun = false;
                                }
                                realTextView.setText(finalReal);
                            }
                        });
            }
            withAnimation = false;
        } else {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
            realTextView.setText(text);
        }

    }

    public void stopText() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        isRun = false;
    }

    public void startText() {
        realTextView.setText(text);
    }

}
