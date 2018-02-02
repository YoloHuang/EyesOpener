package com.example.hzj.EyeOpener.ui.fragment;

/**
 * Created by hzj on 2017/12/26.
 */

public class AllHotFragment extends BaseHotFragment {
    @Override
    protected void getHotData() {
        mPresenter.getHotData("historical");
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
