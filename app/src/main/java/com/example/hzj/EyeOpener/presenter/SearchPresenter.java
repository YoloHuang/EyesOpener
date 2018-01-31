package com.example.hzj.EyeOpener.presenter;

import android.util.Log;

import com.example.hzj.EyeOpener.base.Contract.SearchContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.SearchResultBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2018/1/25.
 */

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    List<String> hotSearch=new ArrayList<>();
    String nextUrl;
    List<ItemListBean> listBeans=new ArrayList<>();
    List<ItemListBean> moreListBeans=new ArrayList<>();

    @Inject
    public SearchPresenter(DataManager manager){
        this.mDataManager=manager;
    }

    @Override
    public void getHotSearchData() {
        addSubscribe(mDataManager.getHotSearch()
        .compose(RxUtil.<List<String>>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<List<String>>(mView) {
            @Override
            public void onNext(List<String> strings) {
                Log.d("hzj", "onNext: strings"+strings);
                hotSearch=strings;
                mView.showHotSearch(strings);
            }
        }));
    }

    @Override
    public void getSearchData(String query) {
        addSubscribe(mDataManager.getSearchResultBean(0,10,query)
        .compose(RxUtil.<SearchResultBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<SearchResultBean>(mView) {
            @Override
            public void onNext(SearchResultBean searchResultBean) {
                nextUrl=searchResultBean.getNextPageUrl();
                listBeans.clear();
                for(ItemListBean resultBean :searchResultBean.getItemList()){
                        listBeans.add(resultBean);
                }
                mView.showResult(listBeans,searchResultBean.getTotal());
            }
        }));

    }

    @Override
    public void getMoreData(String query) {
        if(nextUrl==null){
            return;
        }else {
            final String startS=nextUrl.substring(nextUrl.indexOf("=")+1,nextUrl.indexOf("&"));
            Log.d("hzj", "getMoreReplyData: "+startS);
            int start= Integer.valueOf(startS).intValue();
            addSubscribe(mDataManager.getSearchResultBean(start,10,query)
            .compose(RxUtil.<SearchResultBean>rxSchedulerHelper())
            .subscribeWith(new CommonSubscriber<SearchResultBean>(mView) {
                @Override
                public void onNext(SearchResultBean searchResultBean) {
                    for(ItemListBean itemListBean:searchResultBean.getItemList()){
                            moreListBeans.add(itemListBean);

                    }
                    mView.showMoreResult(moreListBeans);
                    nextUrl=searchResultBean.getNextPageUrl();
                }
            }));
        }

    }
}
