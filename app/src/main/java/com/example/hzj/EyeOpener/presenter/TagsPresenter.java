package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.Contract.TagContract;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;
import com.example.hzj.EyeOpener.model.bean.TagsBean;
import com.example.hzj.EyeOpener.util.RxUtil;
import com.example.hzj.EyeOpener.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 */

public class TagsPresenter extends RxPresenter<TagContract.View> implements TagContract.Presenter {


    @Inject
    public TagsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    /**
     * 获取所有分类
     */
    @Override
    public void getTagsDatas() {
        addSubscribe(mDataManager.getTagsBean()
                .compose(RxUtil.<List<TagsBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<TagsBean>>(mView) {
                    @Override
                    public void onNext(List<TagsBean> tagsBeans) {
                        mView.showContents(tagsBeans);
                    }
                }));
    }
}
