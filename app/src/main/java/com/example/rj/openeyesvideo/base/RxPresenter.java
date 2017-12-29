package com.example.rj.openeyesvideo.base;

import com.example.rj.openeyesvideo.model.DataManager;
import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by rj on 2017/12/18.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    protected DataManager mDataManager;

    protected  void addSubscribe(Disposable disposable){
        if(mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void clearSubscribe(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }


    public void addHistoryBeanToDB(ItemListBean itemListBean){
        HistoryBean historyBean=new HistoryBean();
    }

    public void addLikeBeanToDB(ItemListBean itemListBean){
        LikeBean likeBean=new LikeBean();
    }

    public ItemListBean getItemListBean(int id){
        return null;
        //mDataManager.
    }

    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    @Override
    public void detachView() {
        this.mView=null;
        clearSubscribe();
    }
}
