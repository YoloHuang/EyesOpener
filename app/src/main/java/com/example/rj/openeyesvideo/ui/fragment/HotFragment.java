package com.example.rj.openeyesvideo.ui.fragment;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.presenter.DailyPresenter;

import java.util.List;

/**
 * Created by rj on 2017/12/25.
 */

public class HotFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {


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
    public void showContent(List<DailyBean.IssueListBean.ItemListBean> list) {

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
