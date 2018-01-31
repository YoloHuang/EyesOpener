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

/**
 * Created by hzj on 2017/12/21.
 */

public interface ApiHelper {
    Flowable<List<String>> getTrendingTagInfo();

    Flowable<SearchResultBean> query(String key,int start);

    Flowable<DailyBean> getDailyBean();
    Flowable<DailyBean> getDailyBean(long date);

    //Flowable<HotBean> getMonthHotBean();
    Flowable<HotBean> getWeekHotBean(String type);
    //Flowable<HotBean> getHistroicalHotBean();
    Flowable<List<TagsBean>> getTagsBean();
    Flowable<TagChildBean> getTagChildBean(int start,int num,int id);
    Flowable<RelateBean> getRelateBean(int id);
    Flowable<ItemListBean.DataBean> getDataBean(int id);
    Flowable<ReplyBean> getReplyBean(int id);
    Flowable<ReplyBean> getMoreReplyBean(int id,int lastId,int num);

    Flowable<List<String>> getHotSearch();
    Flowable<SearchResultBean> getSearchResultBean(int start,int num,String query);

}
