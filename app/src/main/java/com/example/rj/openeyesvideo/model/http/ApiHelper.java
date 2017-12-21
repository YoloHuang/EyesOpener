package com.example.rj.openeyesvideo.model.http;

import com.example.rj.openeyesvideo.model.bean.SearchResultBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by rj on 2017/12/21.
 */

public interface ApiHelper {
    Flowable<List<String>> getTrendingTagInfo();

    Flowable<SearchResultBean> query(String key,int start);
}
