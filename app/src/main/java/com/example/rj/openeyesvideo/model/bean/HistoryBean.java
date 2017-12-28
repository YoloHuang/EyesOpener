package com.example.rj.openeyesvideo.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rj on 2017/12/21.
 */

public class HistoryBean extends RealmObject {
    @PrimaryKey
    private int id;

    private ItemListBean itemListBean;

    public ItemListBean getItemListBean() {
        return itemListBean;
    }

    public void setItemListBean(ItemListBean itemListBean) {
        this.itemListBean = itemListBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
