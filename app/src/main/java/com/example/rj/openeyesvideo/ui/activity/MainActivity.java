package com.example.rj.openeyesvideo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.MainContract;
import com.example.rj.openeyesvideo.presenter.MainPresenter;
import com.example.rj.openeyesvideo.ui.adapter.MainAdapter;
import com.example.rj.openeyesvideo.ui.fragment.DailyFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.view_search)
    MaterialSearchView mMaterialSearchView;
    @BindView(R.id.vp_openeyes)
    ViewPager mViewPager;
    @BindView(R.id.tab_openeyes)
    TabLayout mTabLayout;

    MainAdapter mMainAdapter;
    MenuItem mSearchMenuItem;

    String[] tabTitle = new String[]{"首页","热门","专栏","我"};
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void initEventAndData() {
        toolbar_title.setText(R.string.app_name);
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        mMainAdapter=new MainAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mMainAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);


        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        mMaterialSearchView.setMenuItem(item);
        mSearchMenuItem=item;
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setSearchDisable() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
