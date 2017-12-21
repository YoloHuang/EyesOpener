package com.example.rj.openeyesvideo.model.DB;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import java.util.List;

/**
 * Created by rj on 2017/12/21.
 */

public interface DBHelper {

    void insertReadId(HistoryBean historyBean);



    void insertLikeId(LikeBean likeBean);



    void deleteLikeId(int id);

    List<HistoryBean> getHistoryBeans();

    List<LikeBean> getLikeBeans();

    void checkHistoryTime(int id ,long time);

    void checkLikeTime(int id ,long time);


}
