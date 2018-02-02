package com.example.hzj.EyeOpener.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.DailyContract;
import com.example.hzj.EyeOpener.base.RootFragment;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.presenter.DailyPresenter;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.example.hzj.EyeOpener.ui.activity.SearchActivity;
import com.example.hzj.EyeOpener.ui.adapter.DailyRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/20.
 */

public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.view_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH);
    LinearLayoutManager mLayoutManager;
    DailyRecyclerAdapter mAdapter;
    boolean isLoading = false;
    boolean dataReady = false;
    boolean isVisiable;
    String TAG = "hzj";
    private List<ItemListBean> itemListBeans = new ArrayList<>();
    private List<ItemListBean> firstItemListBeans = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    /**
     * 初始化时，先将title设为透明，当recyclerview中viewPager界面移出屏幕时，设置title可见，并显示日期
     */
    @Override
    protected void initEventAndData() {
        Log.d(TAG, "initEventAndData: ");
        super.initEventAndData();
        initRecyclerView();
        initSwipeRefresh();
        stateLoading();
        isVisiable = true;
        toolbar.setBackgroundColor(0x00000000);
        toolbarTitle.setText("");
        toolbarSearch.setImageResource(R.mipmap.ic_action_search_white);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent();
                searchIntent.setClass(mContext, SearchActivity.class);
                mContext.startActivity(searchIntent);
            }
        });
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DailyRecyclerAdapter(mContext, itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getDailyData();
        //滑动监听，会加载更多数据，并且动态控制title的显示
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setToolBar();
                int lastItemPositon = mLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mLayoutManager.getItemCount();
                if (lastItemPositon >= totalPotions - 4 && dy > 0 && totalPotions > 5) {
                    if (!isLoading) {
                        isLoading = true;
                        mPresenter.getMoreData();
                    }
                }
            }
        });
        //设置item点击事件
        mAdapter.setOnItemClickListener(new DailyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                ItemListBean itemListBean = itemListBeans.get(id - 1);
                intent.putExtra("itemListBean", itemListBean);
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * onstart中需要重新调用viewpager的轮转，并设置其中的JumpShowTextView直接显示文字
     */
    @Override
    public void onStart() {
        super.onStart();
        if (dataReady && isVisiable) {
            mPresenter.startInterval();
            mAdapter.startText();
        }
    }

    /**
     * onstop中需要暂停viewpager的轮播，并暂停JumpShowTextView的逐字显示
     */
    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopText();
        mPresenter.stopInterval();
    }


    /**
     * 当此fragment不可见时，需要暂停viewpager的轮播，暂停JumpShowTextView的逐字显示
     * 并在fragment再次可见时，重新调用View的轮播
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisiable = isVisibleToUser;
        if (isVisibleToUser) {
            if (dataReady) {
                mAdapter.startText();
                mPresenter.startInterval();
            }
        } else {
            if (mPresenter != null) {
                mPresenter.stopInterval();
            }
            if (mAdapter != null) {
                mAdapter.stopText();
            }
        }

    }

    /**
     * 根据viewpager是否可见，设置title
     */
    private void setToolBar() {
        int firstItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        if (firstItemPosition == 0) {
            mPresenter.startInterval();
            toolbar.setBackgroundColor(0x00000000);
            toolbarTitle.setText("");
            toolbarSearch.setImageResource(R.mipmap.ic_action_search_white);
        } else {
            mPresenter.stopInterval();
            if (itemListBeans.size() > 1) {
                ItemListBean itemListBean = itemListBeans.get(firstItemPosition - 1);
                toolbar.setBackgroundColor(Color.WHITE);
                toolbarSearch.setImageResource(R.mipmap.ic_action_search);
                if (itemListBean.getType().equals("textHeader")) {
                    toolbarTitle.setText(itemListBean.getData().getText());
                } else {
                    toolbarTitle.setText(simpleDateFormat.format(itemListBean.getData().getDate()));
                }
            }
        }
    }


    private void initSwipeRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLACK, Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }


    @Override
    public void showContent(List<ItemListBean> list) {
        for (ItemListBean itemListBean : list) {
            if (itemListBean.getType().equals("textHeader") || itemListBean.getType().equals("video")) {
                itemListBeans.add(itemListBean);
            }
        }
        stateStart();
        isLoading = false;
        dataReady = true;
        mAdapter.addDailyData(itemListBeans);
        mPresenter.startInterval();
    }

    @Override
    public void showFirstContent(List<ItemListBean> listBeans) {
        for (ItemListBean itemListBean : listBeans) {
            if (itemListBean.getType().equals("video")) {
                firstItemListBeans.add(itemListBean);
            }
        }
        mAdapter.addTopData(firstItemListBeans);
    }

    /**
     * 轮转viewpager
     */
    @Override
    public void changeTopPageView() {
        mAdapter.changeTopPageView();
    }

}
