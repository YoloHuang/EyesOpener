package com.example.hzj.EyeOpener.ui.activity;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseActivity;
import com.example.hzj.EyeOpener.base.Contract.SearchContract;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.presenter.SearchPresenter;
import com.example.hzj.EyeOpener.ui.view.SearchResultView;
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
    @BindView(R.id.view_search)
    RelativeLayout root;
    @BindView(R.id.view_search_tip)
    RelativeLayout tip;

    SearchResultView searchResultView;
    RelativeLayout.LayoutParams mLayoutParams;
    List<ItemListBean> resultList=new ArrayList<>();

    InputMethodManager inputMethodManager;

    boolean isloading=false;
    String queryString;


    List<String> hotList=new ArrayList<>();

    @Override
    protected void initEventAndData() {
        mPresenter.getHotSearchData();
        inputMethodManager= (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        doSearch.setFocusable(true);
        doSearch.setFocusableInTouchMode(true);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                queryString=hotList.get(position);
                hideInputMethod();
                mPresenter.getSearchData(queryString);
                addProgressView();
                return true;
            }
        });

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled=false;
                if(i== EditorInfo.IME_ACTION_SEARCH ){
                    if(editSearch.getText().toString().equals("")){
                        Toast.makeText(mContext,"请输入要搜索的信息~",Toast.LENGTH_SHORT).show();
                    }else {
                        hideInputMethod();
                        queryString=editSearch.getText().toString();
                        mPresenter.getSearchData(queryString);
                        addProgressView();
                        handled=true;
                    }
                }
                return handled;
            }
        });
        doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSearch.getText().toString().equals("")){
                    Toast.makeText(mContext,"请输入要搜索的信息~",Toast.LENGTH_SHORT).show();
                }else {
                    hideInputMethod();
                    queryString = editSearch.getText().toString();
                    mPresenter.getSearchData(queryString);
                    addProgressView();
                }
            }
        });


    }

    private void hideInputMethod() {
        doSearch.requestFocus();
        Log.d("hzj", "hideInputMethod: =="+inputMethodManager.isActive());
        if(inputMethodManager.isActive()){
            inputMethodManager.hideSoftInputFromWindow(editSearch.getWindowToken(),0);
        }
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
