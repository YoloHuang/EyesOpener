package com.example.rj.openeyesvideo.util;

import android.support.v7.util.DiffUtil;

import com.example.rj.openeyesvideo.model.bean.DailyBean;
import com.example.rj.openeyesvideo.model.bean.TagChildBean;

import java.util.List;

/**
 * Created by rj on 2017/12/25.
 */

public class DiffUtilCallBack extends DiffUtil.Callback {

    private List<DailyBean.IssueListBean.ItemListBean> moldDatas,mNewDatas;


    public DiffUtilCallBack(List<DailyBean.IssueListBean.ItemListBean> OldDatas,List<DailyBean.IssueListBean.ItemListBean> NewDatas){
        this.moldDatas=OldDatas;
        this.mNewDatas=NewDatas;
    }


    @Override
    public int getOldListSize() {
        return moldDatas!=null? moldDatas.size():0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas!=null?mNewDatas.size():0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return moldDatas.get(oldItemPosition).getData().getTitle().equals(mNewDatas.get(newItemPosition).getData().getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        DailyBean.IssueListBean.ItemListBean oldItem=moldDatas.get(oldItemPosition);
        DailyBean.IssueListBean.ItemListBean newItem=mNewDatas.get(newItemPosition);
        if(!oldItem.getData().getTags().get(0).getName().equals(newItem.getData().getTags().get(0).getName())){
            return false;
        }
        return true;
    }
}
