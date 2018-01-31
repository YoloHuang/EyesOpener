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

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by hzj on 2017/12/21.
 */

public class RetrofitHelper implements ApiHelper {
    private Api api;

    @Inject
    public RetrofitHelper(Api api){this.api=api;}

    @Override
    public Flowable<List<String>> getTrendingTagInfo() {
        return api.getTrendingTag();
    }

    @Override
    public Flowable<SearchResultBean> query(String key, int start) {
        return api.query(key,start);
    }

    @Override
    public Flowable<DailyBean> getDailyBean() {
        return api.getDailyBean();
    }

    @Override
    public Flowable<DailyBean> getDailyBean(long date) {
        return api.getDailyBean(date);
    }

//    @Override
//    public Flowable<HotBean> getMonthHotBean() {
//        return api.getMonthHotBean();
//    }

    @Override
    public Flowable<HotBean> getWeekHotBean(String type) {
        return api.getWeekHotBean(type);
    }

    @Override
    public Flowable<List<TagsBean>> getTagsBean() {
        return api.getTagsBean();
    }

    @Override
    public Flowable<TagChildBean> getTagChildBean(int start, int num, int id) {
        return api.getTagChildBean(start,num,id);
    }

    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return api.getRelateBean(id);
    }

    @Override
    public Flowable<ItemListBean.DataBean> getDataBean(int id) {
        return api.getDataBean(id);
    }

    @Override
    public Flowable<ReplyBean> getReplyBean(int id) {
        return api.getReplyBean(id);
    }

    @Override
    public Flowable<ReplyBean> getMoreReplyBean(int id, int lastId, int num) {
        return api.getMoreReplyBean(id,lastId,num);
    }

    @Override
    public Flowable<List<String>> getHotSearch() {
        return api.getHotSearch();
    }

    @Override
    public Flowable<SearchResultBean> getSearchResultBean(int start, int num, String query) {
        return api.getSearchResultBean(start,num,query);
    }

//    @Override
//    public Flowable<HotBean> getHistroicalHotBean() {
//        return api.getHistoricalHotBean();
//    }
}
