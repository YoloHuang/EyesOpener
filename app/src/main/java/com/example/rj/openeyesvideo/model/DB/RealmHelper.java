package com.example.rj.openeyesvideo.model.DB;

import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rj on 2017/12/21.
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME="myrealm.vedio";
    private Realm mRealm;

    @Inject
    public RealmHelper(){
        mRealm=Realm.getInstance(new RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .name(DB_NAME)
        .build());
    }

    /**
     * 增加阅读数据
     * @param historyBean
     */
    @Override
    public void insertReadId(HistoryBean historyBean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(historyBean);
        mRealm.commitTransaction();
    }



    @Override
    public void insertLikeId(LikeBean likeBean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(likeBean);
        mRealm.commitTransaction();
    }


    @Override
    public void deleteLikeId(int id) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean!=null){
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    @Override
    public List<HistoryBean> getHistoryBeans() {
        RealmResults<HistoryBean> realmResults=mRealm.where(HistoryBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public List<LikeBean> getLikeBeans() {
        RealmResults<LikeBean> realmResults=mRealm.where(LikeBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public void checkHistoryTime(int id, long time) {

    }

    @Override
    public void checkLikeTime(int id, long time) {

    }

    @Override
    public HistoryBean getHistoryBean(int id) {
        RealmQuery<HistoryBean> historyBean;
        historyBean = mRealm.where(HistoryBean.class).equalTo("id",id);
        return null;
    }
}
