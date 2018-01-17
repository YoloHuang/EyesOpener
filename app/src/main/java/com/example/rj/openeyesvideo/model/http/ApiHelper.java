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

/**
 * Created by rj on 2017/12/21.
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
}
