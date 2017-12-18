package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.Contract.WelcomeContract;
import com.example.rj.openeyesvideo.base.RxPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by rj on 2017/12/18.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME=2200;

    @Inject
    public WelcomePresenter(){

    }

    @Override
    public void getData() {
        int image=R.mipmap.welcome_bg;
        mView.showContent(image);
        startCountDowm();
    }

    private void startCountDowm() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));
    }
}
