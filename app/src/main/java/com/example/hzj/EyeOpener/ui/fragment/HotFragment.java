package com.example.hzj.EyeOpener.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseFragment;
import com.example.hzj.EyeOpener.base.Contract.HotContract;
import com.example.hzj.EyeOpener.presenter.HotPresenter;
import com.example.hzj.EyeOpener.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/25.
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View {

    @BindView(R.id.vp_test)
    ViewPager mViewPager;
    @BindView(R.id.tag_test)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;


    String[] tabTitle = new String[]{"周排行","月排行","总排行"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    MainAdapter mMainAdapter;



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("热门");
        toolbarSearch.setVisibility(View.GONE);
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
