package com.example.rj.openeyesvideo.model.bean;

import java.util.List;

/**
 * Created by rj on 2017/12/21.
 */

public class SearchResultBean {

    public int count;
    public int total;
    public List<DailyBean.IssueListBean.ItemListBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DailyBean.IssueListBean.ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<DailyBean.IssueListBean.ItemListBean> itemList) {
        this.itemList = itemList;
    }
}
