package com.example.rj.openeyesvideo.ui.activity;



import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.SearchContract;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.presenter.SearchPresenter;
import com.example.rj.openeyesvideo.ui.adapter.SearchHotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.iv_dosearch)
    ImageView doSearch;
    @BindView(R.id.et_search)
    EditText editSearch;
    @BindView(R.id.rv_search_hot)
    RecyclerView hotRecyclerView;


    SearchHotAdapter hotAdapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout root;


    List<String> hotList=new ArrayList<>();

    @Override
    protected void initEventAndData() {
        hotAdapter=new SearchHotAdapter(mContext,hotList);
        gridLayoutManager=new GridLayoutManager(mContext,3);
        hotRecyclerView.setAdapter(hotAdapter);
        hotRecyclerView.setLayoutManager(gridLayoutManager);
        mPresenter.getHotSearchData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void showHotSearch(List<String> stringList) {
        Log.d("hzj", "showHotSearch: "+stringList.get(0));
        this.hotList=stringList;
        hotAdapter.getHotData(hotList);
    }

    @Override
    public void showResult(List<ItemListBean> listBeans) {

    }

    @Override
    public void showMoreResult(List<ItemListBean> listBeans) {

    }

    @Override
    protected void initInject() {
    getActivityComponent().inject(this);
    }
}
