package com.example.hzj.EyeOpener.model;

import android.util.Log;

import com.example.hzj.EyeOpener.APP.Constants;
import com.example.hzj.EyeOpener.model.DB.DBHelper;
import com.example.hzj.EyeOpener.model.bean.DailyBean;
import com.example.hzj.EyeOpener.model.bean.DownloadBean;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.HotBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;
import com.example.hzj.EyeOpener.model.bean.RelateBean;
import com.example.hzj.EyeOpener.model.bean.ReplyBean;
import com.example.hzj.EyeOpener.model.bean.SearchResultBean;
import com.example.hzj.EyeOpener.model.bean.TagChildBean;
import com.example.hzj.EyeOpener.model.bean.TagsBean;
import com.example.hzj.EyeOpener.model.http.ApiHelper;
import com.example.hzj.EyeOpener.model.prefs.PreferenceHelper;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by hzj on 2017/12/18.
 * datamanager，所有数据处理的统一管理类
 */

public class DataManager implements ApiHelper, DBHelper, PreferenceHelper {

    ApiHelper mApiHelper;
    DBHelper mDBHelper;
    PreferenceHelper mpreferenceHelper;


    public DataManager(ApiHelper apiHelper, DBHelper dbHelper, PreferenceHelper preferenceHelper) {
        this.mApiHelper = apiHelper;
        this.mDBHelper = dbHelper;
        this.mpreferenceHelper = preferenceHelper;
    }

    /**
     * 获取每日数据
     *
     * @return
     */
    @Override
    public Flowable<DailyBean> getDailyBean() {
        return mApiHelper.getDailyBean();
    }

    /**
     * 获取之前日期数据
     *
     * @param date
     * @return
     */
    @Override
    public Flowable<DailyBean> getDailyBean(long date) {
        return mApiHelper.getDailyBean(date);
    }

    /**
     * 获取热门数据，
     *
     * @param type week,month,history
     * @return
     */
    @Override
    public Flowable<HotBean> getWeekHotBean(String type) {
        return mApiHelper.getWeekHotBean(type);
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    @Override
    public Flowable<List<TagsBean>> getTagsBean() {
        return mApiHelper.getTagsBean();
    }

    /**
     * 获取该分类下所有视频
     *
     * @param start
     * @param num
     * @param id
     * @return
     */
    @Override
    public Flowable<TagChildBean> getTagChildBean(int start, int num, int id) {
        return mApiHelper.getTagChildBean(start, num, id);
    }

    /**
     * 根据视频ID，获取相关推荐视频
     *
     * @param id
     * @return
     */
    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return mApiHelper.getRelateBean(id);
    }

    /**
     * 根据ID来获取dataBean
     *
     * @param id
     * @return
     */
    @Override
    public Flowable<ItemListBean.DataBean> getDataBean(int id) {
        return mApiHelper.getDataBean(id);
    }

    /**
     * 根据ID获取评论内容
     *
     * @param id
     * @return
     */
    @Override
    public Flowable<ReplyBean> getReplyBean(int id) {
        return mApiHelper.getReplyBean(id);
    }

    /**
     * 根据ID获取更多评论内容
     *
     * @param id
     * @return
     */
    @Override
    public Flowable<ReplyBean> getMoreReplyBean(int id, int lastId, int num) {
        return mApiHelper.getMoreReplyBean(id, lastId, num);
    }

    /**
     * 获取搜索热门
     *
     * @return
     */
    @Override
    public Flowable<List<String>> getHotSearch() {
        return mApiHelper.getHotSearch();
    }

    /**
     * 根据搜索关键词获取内容
     *
     * @param query
     * @param start
     * @return
     */
    @Override
    public Flowable<SearchResultBean> getSearchResultBean(int start, int num, String query) {
        return mApiHelper.getSearchResultBean(start, num, query);
    }

    /**
     * 增加阅读数据
     *
     * @param historyBean
     */
    @Override
    public void insertReadId(ItemListBean historyBean) {
        mDBHelper.insertReadId(historyBean);
    }

    /**
     * 增加喜欢数据
     *
     * @param likeBean
     */
    @Override
    public void insertLikeId(ItemListBean likeBean) {
        mDBHelper.insertLikeId(likeBean);
    }

    /**
     * 删除喜欢数据
     *
     * @param id
     */
    @Override
    public void deleteLikeId(int id) {
        mDBHelper.deleteLikeId(id);
    }

    /**
     * 删除阅读记录
     *
     * @param id
     */
    @Override
    public void deleteReadId(int id) {
        mDBHelper.deleteReadId(id);
    }

    /**
     * 获取所有的历史观看数据
     *
     * @return
     */
    @Override
    public List<HistoryBean> getHistoryBeans() {
        return mDBHelper.getHistoryBeans();
    }

    /**
     * 获取所有的喜欢数据
     *
     * @return
     */
    @Override
    public List<LikeBean> getLikeBeans() {
        return mDBHelper.getLikeBeans();
    }

    /**
     * 检测是否有此观看记录
     *
     * @param id
     * @return
     */
    @Override
    public HistoryBean getHistoryBean(int id) {
        return mDBHelper.getHistoryBean(id);
    }

    /**
     * 检测是否有此喜欢记录
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkLike(int id) {
        return mDBHelper.checkLike(id);
    }

    /**
     * 检测是否有此下载记录
     *
     * @param id
     * @return
     */
    @Override
    public int checkDownload(int id) {
        return mDBHelper.checkDownload(id);
    }

    /**
     * 获取所有的下载数据
     *
     * @return
     */
    @Override
    public List<DownloadBean> getDownloadBeans() {
        return mDBHelper.getDownloadBeans();
    }

    /**
     * 增加下载记录
     *
     * @param itemListBean
     */
    @Override
    public void insertDownloadId(ItemListBean itemListBean) {
        mDBHelper.insertDownloadId(itemListBean);
    }

    /**
     * 删除下载记录
     *
     * @param id
     */
    @Override
    public void deleteDownloadId(int id) {
        mDBHelper.deleteDownloadId(id);
    }

    /**
     * 设置为下载完成
     *
     * @param id
     */
    @Override
    public void setDownload(int id) {
        mDBHelper.setDownload(id);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param itemListBean
     */
    public void download(String url, final ItemListBean itemListBean) {
        FileDownloader.getImpl().create(url)
                .setPath(Constants.PATH_DOWNLOAD)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Log.d("hzj", "progress:soFarBytes ==" + soFarBytes + "--totalBytes==" + totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        setDownload(itemListBean.getData().getId());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();
    }

    /**
     * 获取关于是否使用流量播放的设置
     *
     * @return
     */
    @Override
    public boolean getPlaySetting() {
        return mpreferenceHelper.getPlaySetting();
    }

    /**
     * 设置是否使用流量观看
     *
     * @param playSetting
     */
    @Override
    public void setPlaySetting(boolean playSetting) {
        mpreferenceHelper.setPlaySetting(playSetting);
    }

    /**
     * 获取是否使用流量下载的设置
     *
     * @return
     */
    @Override
    public boolean getDowmloadSetting() {
        return mpreferenceHelper.getDowmloadSetting();
    }

    /**
     * 设置是否使用流量下载
     *
     * @param downloadSetting
     */
    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mpreferenceHelper.setDownloadSetting(downloadSetting);
    }
}
