package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.HotTopContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.HotBean;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 */

public class WeekPresenter extends RxPresenter<HotTopContract.View> implements HotTopContract.Presenter {

    List<ItemListBean> itemListBeans;

    @Inject
    public WeekPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    /**
     * 获取当前type下所有热门内容
     *
     * @param type
     */
    @Override
    public void getHotData(String type) {
        addSubscribe(mDataManager.getWeekHotBean(type)
                .compose(RxUtil.<HotBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<HotBean>(mView) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        itemListBeans = hotBean.getItemList();
                        mView.showContents(itemListBeans);
                    }
                }));
    }
}
