package com.example.rj.openeyesvideo.presenter;

import com.example.rj.openeyesvideo.base.Contract.TagContract;
import com.example.rj.openeyesvideo.base.RxPresenter;
import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.TagsBean;
import com.example.rj.openeyesvideo.util.RxUtil;
import com.example.rj.openeyesvideo.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rj on 2017/12/26.
 */

public class TagsPresenter extends RxPresenter<TagContract.View> implements TagContract.Presenter{



    @Inject
    public TagsPresenter(DataManager dataManager){
        mDataManager=dataManager;
    }

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
