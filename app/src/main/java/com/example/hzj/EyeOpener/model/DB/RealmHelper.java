package com.example.hzj.EyeOpener.model.DB;

import com.example.hzj.EyeOpener.model.bean.DownloadBean;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by hzj on 2017/12/21.
 * 数据库操作类
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "myrealm.vedio";
    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * 增加阅读数据
     *
     * @param itemListBean
     */
    @Override
    public void insertReadId(ItemListBean itemListBean) {
        HistoryBean historyBean = new HistoryBean();
        historyBean.setId(itemListBean.getData().getId());
        historyBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        historyBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        historyBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
        if (itemListBean.getData().getCover() == null) {
            historyBean.setImage(itemListBean.getData().getCoverForFeed());
        } else {
            historyBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        historyBean.setTitle(itemListBean.getData().getTitle());
        historyBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(historyBean);
        mRealm.commitTransaction();
    }


    /**
     * 增加喜欢数据
     *
     * @param itemListBean
     */
    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        LikeBean likeBean = new LikeBean();
        likeBean.setId(itemListBean.getData().getId());
        likeBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        likeBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        likeBean.setAuthorSlogen(itemListBean.getData().getCategory());
        if (itemListBean.getData().getCover() == null) {
            likeBean.setImage(itemListBean.getData().getCoverForFeed());
        } else {
            likeBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        likeBean.setTitle(itemListBean.getData().getTitle());
        likeBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(likeBean);
        mRealm.commitTransaction();
    }


    /**
     * 删除喜欢数据
     *
     * @param id
     */
    @Override
    public void deleteLikeId(int id) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    /**
     * 删除阅读记录
     *
     * @param id
     */
    @Override
    public void deleteReadId(int id) {
        HistoryBean bean = mRealm.where(HistoryBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    /**
     * 获取所有的历史观看数据
     *
     * @return
     */
    @Override
    public List<HistoryBean> getHistoryBeans() {
        RealmResults<HistoryBean> realmResults = mRealm.where(HistoryBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(realmResults);
    }

    /**
     * 获取所有的喜欢数据
     *
     * @return
     */
    @Override
    public List<LikeBean> getLikeBeans() {
        RealmResults<LikeBean> realmResults = mRealm.where(LikeBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(realmResults);
    }

    /**
     * 检测是否有此观看记录
     *
     * @param id
     * @return
     */
    @Override
    public HistoryBean getHistoryBean(int id) {
        HistoryBean historyBean;
        historyBean = mRealm.where(HistoryBean.class).equalTo("id", id).findFirst();
        if (historyBean == null) {
            return null;
        } else {
            return mRealm.copyFromRealm(historyBean);
        }
    }

    /**
     * 检测是否有此喜欢记录
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkLike(int id) {
        LikeBean likeBean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        if (likeBean == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检测是否有此下载记录
     *
     * @param id
     * @return
     */
    @Override
    public int checkDownload(int id) {
        DownloadBean downloadBean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        if (downloadBean == null) {
            return 0;
        } else {
            return downloadBean.getIsdownload();
        }
    }

    /**
     * 获取所有的下载数据
     *
     * @return
     */
    @Override
    public List<DownloadBean> getDownloadBeans() {
        RealmResults<DownloadBean> list = mRealm.where(DownloadBean.class).findAllSorted("time", Sort.ASCENDING);
        return mRealm.copyFromRealm(list);
    }

    /**
     * 增加下载记录
     *
     * @param itemListBean
     */
    @Override
    public void insertDownloadId(ItemListBean itemListBean) {
        DownloadBean downloadBean = new DownloadBean();
        downloadBean.setId(itemListBean.getData().getId());
        downloadBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        downloadBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        downloadBean.setAuthorSlogen(itemListBean.getData().getCategory());
        downloadBean.setIsdownload(1);
        if (itemListBean.getData().getCover() == null) {
            downloadBean.setImage(itemListBean.getData().getCoverForFeed());
        } else {
            downloadBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        downloadBean.setTitle(itemListBean.getData().getTitle());
        downloadBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(downloadBean);
        mRealm.commitTransaction();
    }

    /**
     * 删除下载记录
     *
     * @param id
     */
    @Override
    public void deleteDownloadId(int id) {
        DownloadBean bean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    /**
     * 设置为下载完成
     *
     * @param id
     */
    @Override
    public void setDownload(int id) {
        DownloadBean bean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.setIsdownload(2);
        }
        mRealm.commitTransaction();
    }
}
