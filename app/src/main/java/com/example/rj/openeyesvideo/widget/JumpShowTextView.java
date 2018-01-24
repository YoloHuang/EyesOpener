package com.example.rj.openeyesvideo.widget;

import android.content.Context;
import android.content.res.TypedArray;

import android.graphics.Color;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
    boolean withAnimation=true;
    private Observable observable;


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
        //isRun=false;
        //initText(text);
        placeHolder.setText(text);
        placeHolder.setVisibility(INVISIBLE);
        startView();
    }

    private void createText() {
        Log.d("hzj", "createText: textColor"+textColor);

        placeHolder=new TextView(context);
        placeHolder.getPaint().setTextSize(textSize);
        placeHolder.setSingleLine(isSinglen);
        placeHolder.setTextColor(textColor);
        placeHolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        placeHolder.setVisibility(INVISIBLE);

        realTextView=new TextView(context);
        realTextView.getPaint().setTextSize(textSize);
        realTextView.setSingleLine(isSinglen);
        realTextView.setTextColor(textColor);
        realTextView.getPaint().setFakeBoldText(isBold);
        realTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
    private void initView() {
        removeAllViews();
        addView(placeHolder);
        addView(realTextView);
    }

    boolean isRun=false;

    private void startView(){
        time=1000/text.length();
        if(withAnimation){

        }
        if (isRun){
            realTextView.setText(text);
        }else {
            for(int i=0;i<text.length();i++){
                Observable.interval(time,TimeUnit.MILLISECONDS)
                        .take(i)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                String finalReal=text.substring(0,aLong.intValue());
                                realTextView.setText(finalReal);
                                isRun=true;
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                isRun=false;
                            }
//
                        });
            }
            isRun=true;
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
