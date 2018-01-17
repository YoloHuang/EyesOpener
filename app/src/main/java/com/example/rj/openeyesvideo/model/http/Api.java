package com.example.rj.openeyesvideo.model.http;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.RelateBean;
import com.example.rj.openeyesvideo.model.bean.ReplyBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;
import com.example.rj.openeyesvideo.model.bean.TagsBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    //每日推荐
    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean();

    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean(@Query("date") long date);



    //热门
    @GET("v3/ranklist?num=10")
    Flowable<HotBean> getWeekHotBean(@Query("strategy") String type);


    //所有分类
    @GET("v2/categories")
    Flowable<List<TagsBean>> getTagsBean();


    //分类视频
    @GET("v4/categories/videoList?strategy=date")
    Flowable<TagChildBean> getTagChildBean(@Query("start")int start,@Query("num")int num,@Query("id")int id);


    //相关视频推荐
    @GET("v4/video/related?")
    Flowable<RelateBean> getRelateBean(@Query("id")int id);

    @GET("v1/video/{id}")
    Flowable<ItemListBean.DataBean> getDataBean(@Path("id") int id);

    @GET("v2/replies/video?")
    Flowable<ReplyBean> getReplyBean(@Query("videoId")int id);

    @GET("v2/replies/video?")
    Flowable<ReplyBean> getMoreReplyBean(@Query("videoId")int id,@Query("lastId")int lastId,@Query("num")int num);
}
