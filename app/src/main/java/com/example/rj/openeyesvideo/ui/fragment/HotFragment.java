package com.example.rj.openeyesvideo.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseFragment;
import com.example.rj.openeyesvideo.base.Contract.DailyContract;
import com.example.rj.openeyesvideo.base.Contract.HotContract;
import com.example.rj.openeyesvideo.base.RootFragment;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.presenter.DailyPresenter;
import com.example.rj.openeyesvideo.presenter.HotPresenter;
import com.example.rj.openeyesvideo.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by rj on 2017/12/25.
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View {

    @BindView(R.id.vp_test)
    ViewPager mViewPager;
    @BindView(R.id.tag_test)
    TabLayout mTabLayout;


    String[] tabTitle = new String[]{"周排行","月排行","总排行"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    MainAdapter mMainAdapter;



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new WeekFragment());
        fragments.add(new MonthFragment());
        fragments.add(new AllHotFragment());
        mMainAdapter=new MainAdapter(getFragmentManager(),fragments);
        mViewPager.setAdapter(mMainAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateStart() {

    }

    @Override
    public void stateLoading() {

    }
}
