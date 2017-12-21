package com.example.rj.openeyesvideo.model.http;

import com.example.rj.openeyesvideo.model.bean.SearchResultBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rj on 2017/12/21.
 */

public interface Api {

    String HOST="http://baobab.kaiyanapp.com/api/";

    @GET("v3/queries/hot")
    Flowable<List<String>> getTrendingTag();

    @GET("v1/search?num=10")
    Flowable<SearchResultBean> query(@Query("query") String key, @Query("start") int start);
}
