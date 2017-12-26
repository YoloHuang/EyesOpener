package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;

/**
 * Created by rj on 2017/12/26.
 */

public interface HotContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
