package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.DailyBean;

import java.util.List;

/**
 * Created by rj on 2017/12/20.
 */

public interface DailyContract {
    interface View extends BaseView{
        void showContent(List<DailyBean.IssueListBean.ItemListBean> list);
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getMoreData();
    }
}
