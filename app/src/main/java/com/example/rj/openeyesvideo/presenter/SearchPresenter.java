package com.example.rj.openeyesvideo.presenter;

import android.util.Log;

import com.example.rj.openeyesvideo.base.Contract.SearchContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2018/1/25.
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
