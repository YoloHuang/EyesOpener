package com.example.hzj.EyeOpener.model.bean;

import java.util.List;

/**
 * Created by hzj on 2017/12/26.
 */

public class TagChildBean {

    /**
     * itemList : [{"type":"video","data":{"dataType":"VideoBeanForClient","id":69476,"title":"神秘黑盒子之帅气暖男\u2014\u2014杨祐宁","slogan":null,"description":"#智族GQ MOTY年度人物# 神秘黑盒子：低调谦虚又靠谱的暖男@楊祐寧YOYANG 获得了2017 GQ MOTY年度最受期待男艺人奖项，那么风度翩翩的他会在黑盒子里有什么表现呢？","provider":{"name":"PGC","alias":"PGC","icon":""},"category":"时尚","author":{"id":194,"icon":"http://img.kaiyanapp.com/a3c92d9df2f3432330798258c8117c9a.png?imageMogr2/quality/60/format/jpg","name":"GQ 中国","description":"有型有款，智趣不凡","link":"","latestReleaseTime":1514275179000,"videoNum":131,"adTrack":null,"follow":{"itemType":"author","itemId":194,"followed":false},"shield":{"itemType":"author","itemId":194,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/9361ee364d23d04658c2226da2c72389.png?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/9361ee364d23d04658c2226da2c72389.png?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/120ae8a0b981efc2d03f913d6b9c0b1f.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=69476&editionType=default&source=aliyun","thumbPlayUrl":null,"duration":10,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=69476","forWeibo":"http://www.eyepetizer.net/detail.html?vid=69476"},"releaseTime":1514275179000,"library":"NOT_RECOMMEND","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=69476&editionType=high&source=aliyun","size":1171872},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=69476&editionType=high&source=qcloud","size":1171872},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=69476&editionType=high&source=ucloud","size":1171872}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=69476&editionType=high&source=aliyun"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":686,"name":"帅哥","actionUrl":"eyepetizer://tag/686/?title=%E5%B8%85%E5%93%A5","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/af7947a1044086048729b2114b4cb90d.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/af7947a1044086048729b2114b4cb90d.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"},{"id":678,"name":"明星","actionUrl":"eyepetizer://tag/678/?title=%E6%98%8E%E6%98%9F","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/1ba2b63b0e51c55c0a488f2c206c7770.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/45f5ee90bc22990d5bab85b9287b7c09.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"},{"id":26,"name":"时尚","actionUrl":"eyepetizer://tag/26/?title=%E6%97%B6%E5%B0%9A","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/34b720d05d98c905432e9906d5b9cdf7.jpeg?imageMogr2/quality/100","headerImage":"http://img.kaiyanapp.com/34b720d05d98c905432e9906d5b9cdf7.jpeg?imageMogr2/quality/100","tagRecType":"NORMAL"}],"type":"NORMAL","titlePgc":"神秘黑盒子之帅气暖男\u2014\u2014杨祐宁","descriptionPgc":"#智族GQ MOTY年度人物# 神秘黑盒子：低调谦虚又靠谱的暖男@楊祐寧YOYANG 获得了2017 GQ MOTY年度最受期待男艺人奖项，那么风度翩翩的他会在黑盒子里有什么表现呢？","remark":"","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1514275179000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null,"src":null},"tag":null,"id":0,"adIndex":-1}]
     * count : 1
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v4/categories/videoList?start=1&num=1&strategy=date&id=24
     * adExist : false
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private List<ItemListBean> itemList;

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

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }


}
