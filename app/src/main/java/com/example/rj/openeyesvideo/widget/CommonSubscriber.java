package com.example.rj.openeyesvideo.widget;

import android.text.TextUtils;

import com.example.rj.openeyesvideo.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by rj on 2017/12/25.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private BaseView mBaseView;
    String ErrorMsg;

    protected CommonSubscriber(BaseView baseView, String ErrorMsg){
        this.mBaseView=baseView;
        this.ErrorMsg=ErrorMsg;
    }

    protected CommonSubscriber(BaseView baseView){
        this.mBaseView=baseView;
    }

    @Override
    public void onError(Throwable t) {
        if(mBaseView==null){
            return;
        }
        if(ErrorMsg!=null && !TextUtils.isEmpty(ErrorMsg)){
            mBaseView.showErrorMsg(ErrorMsg);
        }else if(t instanceof HttpException){
            mBaseView.showErrorMsg("数据加载失败~");
        }else {
            mBaseView.showErrorMsg("未知错误= = ！");
        }
    }

    @Override
    public void onComplete() {

    }
}
