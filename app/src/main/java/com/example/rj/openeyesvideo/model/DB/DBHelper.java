package com.example.rj.openeyesvideo.model.DB;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.DownloadBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;
import com.example.rj.openeyesvideo.ui.view.ListEndView;

import java.util.List;

/**
 * Created by rj on 2017/12/21.
 */

public interface DBHelper {

    void insertReadId(ItemListBean itemListBean);



    void insertLikeId(ItemListBean itemListBean);



    void deleteLikeId(int id);

    void deleteReadId(int id);

    List<HistoryBean> getHistoryBeans();

    List<LikeBean> getLikeBeans();

    void checkHistoryTime(int id ,long time);

    void checkLikeTime(int id ,long time);

    HistoryBean getHistoryBean(int id);

    boolean checkLike(int id);

    int checkDownload(int id);

    List<DownloadBean> getDownloadBeans();
    void insertDownloadId(ItemListBean itemListBean);
    void deleteDownloadId(int id);
    void setDownload(int id);
}
