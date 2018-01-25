package com.example.rj.openeyesvideo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rj.openeyesvideo.APP.App;
import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.MainContract;
import com.example.rj.openeyesvideo.presenter.MainPresenter;
import com.example.rj.openeyesvideo.ui.adapter.MainAdapter;
import com.example.rj.openeyesvideo.ui.fragment.DailyFragment;
import com.example.rj.openeyesvideo.ui.fragment.HotFragment;
import com.example.rj.openeyesvideo.ui.fragment.MyFragment;
import com.example.rj.openeyesvideo.ui.fragment.TagsFragment;
import com.example.rj.openeyesvideo.util.SystemUtil;
import com.liulishuo.filedownloader.FileDownloader;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

//    @BindView(R.id.toolbar_title)
//    TextView toolbar_title;
//    @BindView(R.id.view_search)
//    MaterialSearchView mMaterialSearchView;
    @BindView(R.id.vp_openeyes)
    ViewPager mViewPager;
    @BindView(R.id.tab_openeyes)
    TabLayout mTabLayout;
//    @BindView(R.id.toolbar)
//    RelativeLayout mToolbar;
//    @BindView(R.id.iv_tab)
//    ImageView imageTab;
//    @BindView(R.id.tv_tab)
//    TextView textTab;

    MainAdapter mMainAdapter;
    MenuItem mSearchMenuItem;
    private long exitTime;

    String[] tabTitle = new String[]{"首页","热门","专栏","我"};
    int[] tabIcon=new int[]{
      R.mipmap.ic_tab_strip_icon_feed,R.mipmap.ic_tab_strip_icon_follow,R.mipmap.ic_tab_strip_icon_category,R.mipmap.ic_tab_strip_icon_profile
    };
    int[] tabIconSelect=new int[]{R.mipmap.ic_tab_strip_icon_feed_selected,R.mipmap.ic_tab_strip_icon_follow_selected,R.mipmap.ic_tab_strip_icon_category_selected,R.mipmap.ic_tab_strip_icon_profile_selected};
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void initEventAndData() {
        FileDownloader.setup(mContext);
        //toolbar_title.setText(R.string.app_name);
        fragments.add(new DailyFragment());
        fragments.add(new HotFragment());
        fragments.add(new TagsFragment());
        fragments.add(new MyFragment());
        mMainAdapter=new MainAdapter(getSupportFragmentManager(),fragments);

        mViewPager.setAdapter(mMainAdapter);
//        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
//        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
//        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
//        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i=0;i<mMainAdapter.getCount();i++){
            TabLayout.Tab tab=mTabLayout.getTabAt(i);
            tab.setCustomView(R.layout.item_tabtitle);
            TextView textView=tab.getCustomView().findViewById(R.id.tv_tab);
            textView.setText(tabTitle[i]);
            if(i==0)textView.setSelected(true);
            ImageView imageView=tab.getCustomView().findViewById(R.id.iv_tab);
            if(i==0){
                imageView.setImageResource(tabIconSelect[i]);
            }else {
                imageView.setImageResource(tabIcon[i]);
            }
        }
//        mTabLayout.getTabAt(0).setText(tabTitle[0]).setIcon(tabIcon[0]);
//        mTabLayout.getTabAt(1).setText(tabTitle[1]).setIcon(tabIcon[1]);
//        mTabLayout.getTabAt(2).setText(tabTitle[2]).setIcon(tabIcon[2]);
//        mTabLayout.getTabAt(3).setText(tabTitle[3]).setIcon(tabIcon[3]);
//        mTabLayout.getTabAt(0).select();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               // tab.setIcon(tabIconSelect[tab.getPosition()]);
                ImageView imageView= tab.getCustomView().findViewById(R.id.iv_tab);
                TextView textView=tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(true);
                imageView.setImageResource(tabIconSelect[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab.setIcon(tabIcon[tab.getPosition()]);
                ImageView imageView= tab.getCustomView().findViewById(R.id.iv_tab);
                TextView textView=tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(false);
                imageView.setImageResource(tabIcon[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        mMaterialSearchView.setVoiceSearch(false);
//        mMaterialSearchView.setCursorDrawable(R.drawable.custom_cursor);
//        mPresenter.getSearchSuggestions();
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        if(System.currentTimeMillis()-exitTime>2000){
            Toast.makeText(mContext,"连按两次退出哦~",Toast.LENGTH_SHORT).show();
            exitTime= System.currentTimeMillis();
        }else {
            finish();
            App.exitApp();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setSearchDisable() {

    }


    @Override
    public void showSearchSuggestions(List<String> strings) {
        //mMaterialSearchView.setSuggestions(strings);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
