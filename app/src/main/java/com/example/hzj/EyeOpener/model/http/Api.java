package com.example.hzj.EyeOpener.model.http;

import com.example.hzj.EyeOpener.model.bean.DailyBean;
import com.example.hzj.EyeOpener.model.bean.HotBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.RelateBean;
import com.example.hzj.EyeOpener.model.bean.ReplyBean;
import com.example.hzj.EyeOpener.model.bean.SearchResultBean;
import com.example.hzj.EyeOpener.model.bean.TagChildBean;
import com.example.hzj.EyeOpener.model.bean.TagsBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hzj on 2017/12/21.
 */

public interface Api {

    String HOST = "http://baobab.kaiyanapp.com/api/";

    /**
     * 获取每日数据
     *
     * @return
     */
    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean();

    /**
     * 获取之前日期数据
     *
     * @param date
     * @return
     */
    @GET("v2/feed?num=2")
    Flowable<DailyBean> getDailyBean(@Query("date") long date);


    /**
     * 获取热门数据，
     *
     * @param type week,month,history
     * @return
     */
    @GET("v3/ranklist?num=10")
    Flowable<HotBean> getWeekHotBean(@Query("strategy") String type);


    /**
     * 获取所有分类
     *
     * @return
     */
    @GET("v2/categories")
    Flowable<List<TagsBean>> getTagsBean();


    /**
     * 获取该分类下所有视频
     *
     * @param start
     * @param num
     * @param id
     * @return
     */
    @GET("v4/categories/videoList?strategy=date")
    Flowable<TagChildBean> getTagChildBean(@Query("start") int start, @Query("num") int num, @Query("id") int id);


    /**
     * 根据视频ID，获取相关推荐视频
     *
     * @param id
     * @return
     */
    @GET("v4/video/related?")
    Flowable<RelateBean> getRelateBean(@Query("id") int id);

    /**
     * 根据ID来获取dataBean
     *
     * @param id
     * @return
     */
    @GET("v1/video/{id}")
    Flowable<ItemListBean.DataBean> getDataBean(@Path("id") int id);

    /**
     * 根据ID获取评论内容
     *
     * @param id
     * @return
     */
    @GET("v2/replies/video?")
    Flowable<ReplyBean> getReplyBean(@Query("videoId") int id);

    /**
     * 根据ID获取更多评论内容
     *
     * @param id
     * @return
     */
    @GET("v2/replies/video?")
    Flowable<ReplyBean> getMoreReplyBean(@Query("videoId") int id, @Query("lastId") int lastId, @Query("num") int num);


    /**
     * 获取搜索热门
     *
     * @return
     */
    @GET("v3/queries/hot")
    Flowable<List<String>> getHotSearch();

    /**
     * 根据搜索关键词获取内容
     *
     * @param query
     * @param start
     * @return
     */
    @GET("v1/search?")
    Flowable<SearchResultBean> getSearchResultBean(@Query("start") int start, @Query("num") int num, @Query("query") String query);
}
