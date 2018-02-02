package com.example.hzj.EyeOpener.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzj.EyeOpener.APP.App;
import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseActivity;
import com.example.hzj.EyeOpener.base.Contract.MainContract;
import com.example.hzj.EyeOpener.presenter.MainPresenter;
import com.example.hzj.EyeOpener.ui.adapter.MainAdapter;
import com.example.hzj.EyeOpener.ui.fragment.DailyFragment;
import com.example.hzj.EyeOpener.ui.fragment.HotFragment;
import com.example.hzj.EyeOpener.ui.fragment.MyFragment;
import com.example.hzj.EyeOpener.ui.fragment.TagsFragment;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.vp_openeyes)
    ViewPager mViewPager;
    @BindView(R.id.tab_openeyes)
    TabLayout mTabLayout;

    MainAdapter mMainAdapter;
    String[] tabTitle = new String[]{"首页", "热门", "专栏", "我"};
    int[] tabIcon = new int[]{
            R.mipmap.ic_tab_strip_icon_feed, R.mipmap.ic_tab_strip_icon_follow, R.mipmap.ic_tab_strip_icon_category, R.mipmap.ic_tab_strip_icon_profile
    };
    int[] tabIconSelect = new int[]{R.mipmap.ic_tab_strip_icon_feed_selected, R.mipmap.ic_tab_strip_icon_follow_selected, R.mipmap.ic_tab_strip_icon_category_selected, R.mipmap.ic_tab_strip_icon_profile_selected};
    List<Fragment> fragments = new ArrayList<Fragment>();
    private long exitTime;

    @Override
    protected void initEventAndData() {
        FileDownloader.setup(mContext);
        fragments.add(new DailyFragment());
        fragments.add(new HotFragment());
        fragments.add(new TagsFragment());
        fragments.add(new MyFragment());
        mMainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);

        mViewPager.setAdapter(mMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //自定义TabLayout.Tab
        for (int i = 0; i < mMainAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(R.layout.item_tabtitle);
            TextView textView = tab.getCustomView().findViewById(R.id.tv_tab);
            textView.setText(tabTitle[i]);
            if (i == 0) textView.setSelected(true);
            ImageView imageView = tab.getCustomView().findViewById(R.id.iv_tab);
            if (i == 0) {
                imageView.setImageResource(tabIconSelect[i]);
            } else {
                imageView.setImageResource(tabIcon[i]);
            }
        }
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.iv_tab);
                TextView textView = tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(true);
                imageView.setImageResource(tabIconSelect[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.iv_tab);
                TextView textView = tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(false);
                imageView.setImageResource(tabIcon[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 按两次返回，退出APP
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(mContext, "连按两次退出哦~", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            App.exitApp();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
