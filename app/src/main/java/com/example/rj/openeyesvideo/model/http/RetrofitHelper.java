package com.example.rj.openeyesvideo.model.http;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by rj on 2017/12/21.
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
}
