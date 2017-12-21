package com.example.rj.openeyesvideo.model;

import com.example.rj.openeyesvideo.model.DB.DBHelper;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;
import com.example.rj.openeyesvideo.model.bean.SearchResultBean;
import com.example.rj.openeyesvideo.model.http.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by rj on 2017/12/18.
 */

public class DataManager implements ApiHelper,DBHelper{

    ApiHelper mApiHelper;
    DBHelper mDBHelper;


    public DataManager(ApiHelper apiHelper, DBHelper dbHelper){
        this.mApiHelper=apiHelper;
        this.mDBHelper=dbHelper;
    }

    @Override
    public Flowable<List<String>> getTrendingTagInfo() {
        return mApiHelper.getTrendingTagInfo();
    }

    @Override
    public Flowable<SearchResultBean> query(String key, int start) {
        return mApiHelper.query(key,start);
    }

    @Override
    public void insertReadId(HistoryBean historyBean) {

    }

    @Override
    public void insertLikeId(LikeBean likeBean) {

    }

    @Override
    public void deleteLikeId(int id) {

    }

    @Override
    public List<HistoryBean> getHistoryBeans() {
        return mDBHelper.getHistoryBeans();
    }

    @Override
    public List<LikeBean> getLikeBeans() {
        return mDBHelper.getLikeBeans();
    }

    @Override
    public void checkHistoryTime(int id, long time) {

    }

    @Override
    public void checkLikeTime(int id, long time) {

    }
}
