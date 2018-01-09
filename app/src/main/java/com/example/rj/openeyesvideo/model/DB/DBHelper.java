package com.example.rj.openeyesvideo.model.DB;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import java.util.List;

/**
 * Created by rj on 2017/12/21.
 */

public interface DBHelper {

    void insertReadId(ItemListBean itemListBean);



    void insertLikeId(ItemListBean itemListBean);



    void deleteLikeId(int id);

    List<HistoryBean> getHistoryBeans();

    List<LikeBean> getLikeBeans();

    void checkHistoryTime(int id ,long time);

    void checkLikeTime(int id ,long time);

    HistoryBean getHistoryBean(int id);

    boolean checkLike(int id);
}
