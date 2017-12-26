package com.example.rj.openeyesvideo.model.http;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;
import com.example.rj.openeyesvideo.model.bean.TagsBean;

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

    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean();

    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean(@Query("date") long date);

    @GET("v3/ranklist?num=10")
    Flowable<HotBean> getWeekHotBean(@Query("strategy") String type);

    @GET("v2/categories")
    Flowable<List<TagsBean>> getTagsBean();

    @GET("v4/categories/videoList")
    Flowable<TagChildBean> getTagChildBean(@Query("start")int start,@Query("num")int num,@Query("id")int id);

}
