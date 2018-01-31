package com.example.hzj.EyeOpener.ui.fragment;

/**
 * Created by hzj on 2017/12/26.
 */

public class WeekFragment extends BaseHotFragment {
    @Override
    protected void getHotData() {
        mPresenter.getHotData("weekly");
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
