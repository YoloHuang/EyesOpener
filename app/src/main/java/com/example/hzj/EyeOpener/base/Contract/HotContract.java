package com.example.hzj.EyeOpener.base.Contract;

import com.example.hzj.EyeOpener.base.BasePresenter;
import com.example.hzj.EyeOpener.base.BaseView;

/**
 * Created by hzj on 2017/12/26.
 */

public interface HotContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
