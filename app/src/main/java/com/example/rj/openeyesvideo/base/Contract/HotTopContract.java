package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.HotBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;

import java.util.List;

/**
 * Created by rj on 2017/12/26.
 */

public interface HotTopContract {
    interface View extends BaseView{
        void showContents(List<ItemListBean>  listBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getHotData(String type);
    }
}
