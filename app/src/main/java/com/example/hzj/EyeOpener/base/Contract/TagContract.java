package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.model.bean.TagsBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/26.
 */

public interface TagContract {
    interface View extends BaseView{
        void  showContents(List<TagsBean> tagsBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getTagsDatas();
    }
}
