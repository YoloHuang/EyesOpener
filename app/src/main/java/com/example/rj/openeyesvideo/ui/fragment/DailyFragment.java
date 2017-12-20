package com.example.rj.openeyesvideo.ui.fragment;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.presenter.DailyPresenter;

/**
 * Created by rj on 2017/12/20.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {
    @Override
    public void stateError() {

    }

    @Override
    public void stateStart() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }
}
