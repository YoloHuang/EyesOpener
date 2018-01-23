package com.example.rj.openeyesvideo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.util.TextUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables.*;


/**
 * Created by rj on 2018/1/23.
 */

public class JumpShowTextView extends FrameLayout {

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


    public JumpShowTextView(@NonNull Context context) {
        this(context,null);
    }

    public JumpShowTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        array=context.obtainStyledAttributes(attrs,R.styleable.JumpShowTextView);
        textSize=array.getDimension(R.styleable.JumpShowTextView_textSize,20);
        textColor=array.getColor(R.styleable.JumpShowTextView_textColor,Color.WHITE);
        isBold=array.getBoolean(R.styleable.JumpShowTextView_IsBold,false);
        isSinglen=array.getBoolean(R.styleable.JumpShowTextView_IsSinglen,false);
        createText();
        initView();
    }

    public JumpShowTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context,attrs);
    }

    public void setText(String text){
        this.text=text;
        //initText(text);
        placeHolder.setText(text);
        placeHolder.setVisibility(INVISIBLE);
        startView();
    }

    private void createText() {
        placeHolder=new TextView(context);
        placeHolder.getPaint().setTextSize(textSize);
        placeHolder.setSingleLine(isSinglen);
        placeHolder.getPaint().setColor(textColor);
        placeHolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        placeHolder.setVisibility(INVISIBLE);

        realTextView=new TextView(context);
        realTextView.getPaint().setTextSize(textSize);
        realTextView.setSingleLine(isSinglen);
        placeHolder.getPaint().setColor(textColor);
        realTextView.getPaint().setFakeBoldText(isBold);
        realTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
    private void initView() {
        removeAllViews();
        addView(placeHolder);
        addView(realTextView);
    }
//    private void initText(final String text) {
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                content=TextUtil.getListContent(text);
//                placeHolder.setText(text);
//            }
//        }.run();
//
//    }

    boolean isRun=false;

    private void startView(){
        time=4000/text.length();
        if (isRun){
            realTextView.setText(text);
        }else {
            for(int i=0;i<text.length();i++){
                final String finalReal = text.substring(0,i);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(time);
                            Log.d("hzj", "run: time"+time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        realTextView.setText(finalReal);
                        Log.d("hzj", "startView: finalReal=="+finalReal);
                        isRun=true;
                    }
                }.start();
            }
        }

    }


//    Context context;
//    private String content;
//    private String textAlignment;
//    private ArrayList<String> contents;
//
//    private int textColor;
//    private float textSize;
//    private float density;
//    private TextPaint paint;
//    int count=0;
//    String totalText;
//    long time;
//
//
//
//    public JumpShowTextView(Context context) {
//        this(context,null,0);
//    }
//
//    public JumpShowTextView(Context context, @Nullable AttributeSet attrs) {
//        this(context,attrs,0);
//    }
//
//    public JumpShowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context=context;
//        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.JumpShowTextView);
//        textAlignment=array.getString(R.styleable.JumpShowTextView_TextAlignment);
//        textColor=array.getColor(R.styleable.JumpShowTextView_TextColor, Color.WHITE);
//        textSize=array.getDimension(R.styleable.JumpShowTextView_TextSize,20);
//        initView();
//    }
//
//    public void setContent(final String content) {
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                contents= TextUtil.getListContent(content);
//                Log.d("hzj", "run:contents "+contents.get(0));
//                time=600/contents.size();
//            }
//        }.run();
//    }
//
//    public void setTextAlignment(String textAlignment) {
//        this.textAlignment = textAlignment;
//    }
//
//    public void setTextColor(int textColor) {
//        this.textColor = textColor;
//    }
//
//    public void setTextSize(float textSize) {
//        this.textSize = textSize;
//    }
//
//    private void initView() {
//        this.density=getResources().getDisplayMetrics().density;
//        paint=new TextPaint();
//        paint.setColor(textColor);
//        paint.setTextSize(textSize);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        drawText(canvas);
//    }
//
//    private void drawText(Canvas canvas) {
//        if(count>=contents.size()){
//            return;
//        }
//        StaticLayout layout;
//        totalText+=contents.get(count);
//        Log.d("hzj", "drawText: totalText====="+totalText);
//        layout = new StaticLayout(totalText, paint, getWidth() - (int) (2 * density), Layout.Alignment.ALIGN_NORMAL,(float) 1.5,(float) 0.0,true);
//        canvas.translate(1*density,1*density);
//        layout.draw(canvas);
//        count++;
//        startText();
//    }
//
//    private void startText() {
//        if(count!=contents.size()){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    invalidate();
//                }
//            },time);
//        }
//    }
}
