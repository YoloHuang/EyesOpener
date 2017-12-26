package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;
import com.example.rj.openeyesvideo.model.bean.TagsBean;

import java.util.List;

/**
 * Created by rj on 2017/12/26.
 */

public interface TagContract {
    interface View extends BaseView{
        void  showContents(List<TagsBean> tagsBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getTagsDatas();
    }
}
