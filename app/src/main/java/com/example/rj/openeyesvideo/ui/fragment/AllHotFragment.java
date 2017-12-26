package com.example.rj.openeyesvideo.ui.fragment;

/**
 * Created by rj on 2017/12/26.
 */

public class AllHotFragment extends BaseHotFragment {
    @Override
    protected void getHotData() {
        mPresenter.getHotData("historical");
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this  );
    }
}
