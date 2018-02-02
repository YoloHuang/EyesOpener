package com.example.hzj.EyeOpener.widget;

import android.text.TextUtils;

import com.example.hzj.EyeOpener.base.BaseView;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by hzj on 2017/12/25.
 * 统一观察者
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    String ErrorMsg;
    private BaseView mBaseView;

    protected CommonSubscriber(BaseView baseView, String ErrorMsg) {
        this.mBaseView = baseView;
        this.ErrorMsg = ErrorMsg;
    }

    protected CommonSubscriber(BaseView baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void onError(Throwable t) {
        if (mBaseView == null) {
            return;
        }
        if (ErrorMsg != null && !TextUtils.isEmpty(ErrorMsg)) {
            mBaseView.showErrorMsg(ErrorMsg);
        } else if (t instanceof HttpException) {
            mBaseView.showErrorMsg("数据加载失败~");
        } else {
            mBaseView.showErrorMsg("未知错误= = ！");
        }
    }

    @Override
    public void onComplete() {

    }
}
