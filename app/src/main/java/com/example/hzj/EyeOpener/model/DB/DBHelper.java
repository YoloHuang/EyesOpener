package com.example.hzj.EyeOpener.model.DB;

import com.example.hzj.EyeOpener.model.bean.DownloadBean;
import com.example.hzj.EyeOpener.model.bean.HistoryBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.LikeBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/21.
 * 数据库操作接口类
 */

public interface DBHelper {
    void insertReadId(ItemListBean itemListBean);

    void insertLikeId(ItemListBean itemListBean);


    void deleteLikeId(int id);

    void deleteReadId(int id);

    List<HistoryBean> getHistoryBeans();

    List<LikeBean> getLikeBeans();


    HistoryBean getHistoryBean(int id);

    boolean checkLike(int id);

    int checkDownload(int id);

    List<DownloadBean> getDownloadBeans();

    void insertDownloadId(ItemListBean itemListBean);

    void deleteDownloadId(int id);

    void setDownload(int id);
}
