package com.example.rj.openeyesvideo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by rj on 2017/12/18.
 */

public abstract class SimpleFragment extends SupportFragment {

    protected Activity mActivity;
    protected Context mContext;
    protected View mView;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        mActivity=(Activity)context;
        mContext=context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(),null);
        return mView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();

    }

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();
}
