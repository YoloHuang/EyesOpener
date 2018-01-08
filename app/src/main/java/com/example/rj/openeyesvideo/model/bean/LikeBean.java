package com.example.rj.openeyesvideo.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rj on 2017/12/21.
 */

public class LikeBean extends RealmObject{

    @PrimaryKey
    private int id;

    private String image;
    private String authorName;
    private String authorSlogen;
    private String authorIcon;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSlogen() {
        return authorSlogen;
    }

    public void setAuthorSlogen(String authorSlogen) {
        this.authorSlogen = authorSlogen;
    }

    public String getAuthorIcon() {
        return authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public LikeBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
