package com.example.rj.openeyesvideo.model.DB;

import android.util.Log;

import com.example.rj.openeyesvideo.model.bean.DownloadBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;
import com.example.rj.openeyesvideo.util.SystemUtil;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

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
        Log.d("hzj", "RealmHelper: ");
    }

    /**
     * 增加阅读数据
     * @param itemListBean
     */
    @Override
    public void insertReadId(ItemListBean itemListBean) {
        HistoryBean historyBean=new HistoryBean();
            historyBean.setId(itemListBean.getData().getId());
            historyBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            historyBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            historyBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
            if(itemListBean.getData().getCover()==null){
                historyBean.setImage(itemListBean.getData().getCoverForFeed());
            }else {
                historyBean.setImage(itemListBean.getData().getCover().getFeed());
            }
            historyBean.setTitle(itemListBean.getData().getTitle());
            historyBean.setTime(System.currentTimeMillis());
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(historyBean);
            mRealm.commitTransaction();
        Log.d("hzj", "insertReadId: ");
    }



    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        LikeBean likeBean=new LikeBean();
        likeBean.setId(itemListBean.getData().getId());
        likeBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        likeBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        likeBean.setAuthorSlogen(itemListBean.getData().getCategory());
        if(itemListBean.getData().getCover()==null){
            likeBean.setImage(itemListBean.getData().getCoverForFeed());
        }else {
            likeBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        likeBean.setTitle(itemListBean.getData().getTitle());
        likeBean.setTime(System.currentTimeMillis());
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
    public void deleteReadId(int id) {
        HistoryBean bean=mRealm.where(HistoryBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean!=null){
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    @Override
    public List<HistoryBean> getHistoryBeans() {
        RealmResults<HistoryBean> realmResults=mRealm.where(HistoryBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public List<LikeBean> getLikeBeans() {
        RealmResults<LikeBean> realmResults=mRealm.where(LikeBean.class).findAllSorted("time",Sort.DESCENDING);
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
        HistoryBean historyBean;
        Log.d("hzj", "getHistoryBean: "+id);
        historyBean = mRealm.where(HistoryBean.class).equalTo("id",id).findFirst();
        if(historyBean==null){
            return null;
        }else {
            return mRealm.copyFromRealm(historyBean);
        }
    }

    @Override
    public boolean checkLike(int id) {
        LikeBean likeBean=mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        if(likeBean==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int checkDownload(int id) {
        DownloadBean downloadBean=mRealm.where(DownloadBean.class).equalTo("id",id).findFirst();
        if(downloadBean==null){
            return 0;
        }else {
            return downloadBean.getIsdownload();
        }
    }

    @Override
    public List<DownloadBean> getDownloadBeans() {
        RealmResults<DownloadBean> list=mRealm.where(DownloadBean.class).findAllSorted("time",Sort.ASCENDING);
        return mRealm.copyFromRealm(list);
    }

    @Override
    public void insertDownloadId(ItemListBean itemListBean) {
        DownloadBean downloadBean=new DownloadBean();
        downloadBean.setId(itemListBean.getData().getId());
        downloadBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        downloadBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        downloadBean.setAuthorSlogen(itemListBean.getData().getCategory());
        downloadBean.setIsdownload(1);
        if(itemListBean.getData().getCover()==null){
            downloadBean.setImage(itemListBean.getData().getCoverForFeed());
        }else {
            downloadBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        downloadBean.setTitle(itemListBean.getData().getTitle());
        downloadBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(downloadBean);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteDownloadId(int id) {
        DownloadBean bean = mRealm.where(DownloadBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean!=null){
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    @Override
    public void setDownload(int id) {
        DownloadBean bean=mRealm.where(DownloadBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean!=null){
            bean.setIsdownload(2);
        }
        mRealm.commitTransaction();
    }
}
