package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.WelcomeContract;
import com.example.hzj.EyeOpener.base.RxPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by hzj on 2017/12/18.
 * 由于没有get到开屏欢迎界面API，所以暂时没有办法获取欢迎界面
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;

    @Inject
    public WelcomePresenter() {

    }

    @Override
    public void getData() {
        int image = R.mipmap.welcome_bg;
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
