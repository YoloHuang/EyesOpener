package com.example.rj.openeyesvideo.ui.activity;



import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.SearchContract;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.presenter.SearchPresenter;
import com.example.rj.openeyesvideo.ui.adapter.SearchHotAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.iv_dosearch)
    ImageView doSearch;
    @BindView(R.id.et_search)
    EditText editSearch;
    @BindView(R.id.fl_searchhot)
    TagFlowLayout flowLayout;




    LinearLayoutManager linearLayoutManager;
    RelativeLayout root;


    List<String> hotList=new ArrayList<>();

    @Override
    protected void initEventAndData() {

        mPresenter.getHotSearchData();

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mPresenter.getSearchData(hotList.get(position));
                return true;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void showHotSearch(List<String> stringList) {
        Log.d("hzj", "showHotSearch: "+stringList.get(0));
        this.hotList=stringList;
        flowLayout.setAdapter(new TagAdapter<String>(hotList) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView text=(TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.view_search_hot,parent,false);
                text.setText(hotList.get(position));
                return text;
            }
        });
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
