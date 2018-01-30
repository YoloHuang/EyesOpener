package com.example.rj.openeyesvideo.ui.activity;



import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.BaseActivity;
import com.example.rj.openeyesvideo.base.Contract.SearchContract;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.presenter.SearchPresenter;
import com.example.rj.openeyesvideo.ui.adapter.SearchHotAdapter;
import com.example.rj.openeyesvideo.ui.view.SearchResultView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.iv_dosearch)
    ImageView doSearch;
    @BindView(R.id.et_search)
    EditText editSearch;
    @BindView(R.id.fl_searchhot)
    TagFlowLayout flowLayout;
    @BindView(R.id.view_search)
    RelativeLayout root;
    @BindView(R.id.view_search_tip)
    RelativeLayout tip;

    SearchResultView searchResultView;
    RelativeLayout.LayoutParams mLayoutParams;
    List<ItemListBean> resultList=new ArrayList<>();



    boolean isloading=false;
    String queryString;


    List<String> hotList=new ArrayList<>();

    @Override
    protected void initEventAndData() {
        mPresenter.getHotSearchData();

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                queryString=hotList.get(position);
                mPresenter.getSearchData(queryString);
                addProgressView();
                return true;
            }
        });

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled=false;
                if(i== EditorInfo.IME_ACTION_SEARCH && editSearch.getText()!=null){
                    queryString=editSearch.getText().toString();
                    mPresenter.getSearchData(queryString);
                    addProgressView();
                }
                return false;
            }
        });
        doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSearch.getText()==null){
                    Toast.makeText(mContext,"请输入要搜索的信息~",Toast.LENGTH_SHORT).show();
                }else {
                    queryString = editSearch.getText().toString();
                    mPresenter.getSearchData(queryString);
                    addProgressView();
                }
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
    public void showResult(List<ItemListBean> listBeans, final int total) {
        resultList.clear();
        resultList.addAll(listBeans);
        searchResultView=new SearchResultView(mContext);
        mLayoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        mLayoutParams.addRule(RelativeLayout.BELOW,R.id.view_search_title);
        searchResultView.setLayoutParams(mLayoutParams);
        searchResultView.setdata(resultList);
        root.addView(searchResultView);
        searchResultView.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon= searchResultView.manager.findLastCompletelyVisibleItemPosition();
                int totalPotions=searchResultView.manager.getItemCount();
                if(lastItemPositon>totalPotions-4 && dy>0 && totalPotions<total){
                    if(!isloading){
                        isloading=true;
                        mPresenter.getMoreData(queryString);
                    }
                }
            }
        });
    }

    @Override
    public void showMoreResult(List<ItemListBean> listBeans) {
        if(searchResultView!=null){
            searchResultView.setMoreData(listBeans);
        }
        isloading=false;
    }

    @Override
    public void addProgressView() {
        if(searchResultView!=null){
            root.removeView(searchResultView);
        }
        root.removeView(tip);
    }


    @Override
    protected void initInject() {
    getActivityComponent().inject(this);
    }


    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();

    }
}
