package com.example.rj.openeyesvideo.model.bean;

import java.util.List;

/**
 * Created by rj on 2017/12/26.
 */

public class TestBean {


    private int count;
    private int total;
    private String nextPageUrl;
    private List<ItemListBeanX> itemList;

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

    public List<ItemListBeanX> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBeanX> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBeanX {

        private String type;
        private DataBeanX data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public static class DataBeanX {


            private String dataType;
            private HeaderBean header;
            private int count;
            private List<ItemListBean> itemList;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {
                this.itemList = itemList;
            }

            public static class HeaderBean {


                private int id;
                private String icon;
                private String iconType;
                private String title;
                private Object subTitle;
                private String description;
                private String actionUrl;
                private FollowBean follow;
                private boolean ifPgc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getIconType() {
                    return iconType;
                }

                public void setIconType(String iconType) {
                    this.iconType = iconType;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getSubTitle() {
                    return subTitle;
                }

                public void setSubTitle(Object subTitle) {
                    this.subTitle = subTitle;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public static class FollowBean {
                    /**
                     * itemType : author
                     * itemId : 550
                     * followed : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }
                }
            }

            public static class ItemListBean {
                /**
                 * type : video
                 * data : {"dataType":"VideoBeanForClient","id":51743,"title":"直板全键盘，黑莓 KEYone 测评","slogan":null,"description":"复古流行，黑莓终于转向了Android系统，这款手机走的是稳重商务路线，用了骁龙625芯片，4+64GB存储搭配，尺寸为 149.3x72.5x9.4 毫米，你会为它种草吗？","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"科普","author":{"id":550,"icon":"http://img.kaiyanapp.com/e1ff5d3276a1cd3dfdec54db6bb83ca0.jpeg?imageMogr2/quality/60/format/jpg","name":"Android Authority 安卓权威","description":"美国科技博客，关注安卓生态","link":"","latestReleaseTime":1505729177000,"videoNum":31,"adTrack":null,"follow":{"itemType":"author","itemId":550,"followed":false},"shield":{"itemType":"author","itemId":550,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/1be25fb0ff11446f5147feb7c379341a.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=default&source=aliyun","thumbPlayUrl":null,"duration":728,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51743","forWeibo":"http://wandou.im/3ocd35"},"releaseTime":1505729177000,"library":"DEFAULT","playInfo":[{"height":480,"width":854,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun","size":47206541},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=qcloud","size":47206541},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=ucloud","size":47206541}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun"},{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=aliyun","size":73327847},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=qcloud","size":73327847},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=ucloud","size":73327847}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=aliyun"}],"consumption":{"collectionCount":10,"shareCount":10,"replyCount":3},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":727,"name":"数码测评","actionUrl":"eyepetizer://tag/727/?title=%E6%95%B0%E7%A0%81%E6%B5%8B%E8%AF%84","adTrack":null,"desc":"","bgPicture":"http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"IMPORTANT"},{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"}],"type":"NORMAL","titlePgc":"直板全键盘，黑莓 KEYone 测评","descriptionPgc":"复古流行，黑莓终于转向了Android系统，这款手机走的是稳重商务路线，用了骁龙625芯片，4+64GB存储搭配，尺寸为 149.3x72.5x9.4 毫米，你会为它种草吗？","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505729177000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null,"src":null}
                 * tag : null
                 * id : 0
                 * adIndex : -1
                 */

                private String type;
                private DataBean data;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
                }

                public static class DataBean {
                    /**
                     * dataType : VideoBeanForClient
                     * id : 51743
                     * title : 直板全键盘，黑莓 KEYone 测评
                     * slogan : null
                     * description : 复古流行，黑莓终于转向了Android系统，这款手机走的是稳重商务路线，用了骁龙625芯片，4+64GB存储搭配，尺寸为 149.3x72.5x9.4 毫米，你会为它种草吗？
                     * provider : {"name":"定制来源","alias":"CustomSrc","icon":""}
                     * category : 科普
                     * author : {"id":550,"icon":"http://img.kaiyanapp.com/e1ff5d3276a1cd3dfdec54db6bb83ca0.jpeg?imageMogr2/quality/60/format/jpg","name":"Android Authority 安卓权威","description":"美国科技博客，关注安卓生态","link":"","latestReleaseTime":1505729177000,"videoNum":31,"adTrack":null,"follow":{"itemType":"author","itemId":550,"followed":false},"shield":{"itemType":"author","itemId":550,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true}
                     * cover : {"feed":"http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/1be25fb0ff11446f5147feb7c379341a.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null}
                     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=default&source=aliyun
                     * thumbPlayUrl : null
                     * duration : 728
                     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=51743","forWeibo":"http://wandou.im/3ocd35"}
                     * releaseTime : 1505729177000
                     * library : DEFAULT
                     * playInfo : [{"height":480,"width":854,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun","size":47206541},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=qcloud","size":47206541},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=ucloud","size":47206541}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun"},{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=aliyun","size":73327847},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=qcloud","size":73327847},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=ucloud","size":73327847}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=high&source=aliyun"}]
                     * consumption : {"collectionCount":10,"shareCount":10,"replyCount":3}
                     * campaign : null
                     * waterMarks : null
                     * adTrack : null
                     * tags : [{"id":727,"name":"数码测评","actionUrl":"eyepetizer://tag/727/?title=%E6%95%B0%E7%A0%81%E6%B5%8B%E8%AF%84","adTrack":null,"desc":"","bgPicture":"http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"IMPORTANT"},{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"}]
                     * type : NORMAL
                     * titlePgc : 直板全键盘，黑莓 KEYone 测评
                     * descriptionPgc : 复古流行，黑莓终于转向了Android系统，这款手机走的是稳重商务路线，用了骁龙625芯片，4+64GB存储搭配，尺寸为 149.3x72.5x9.4 毫米，你会为它种草吗？
                     * remark : null
                     * idx : 0
                     * shareAdTrack : null
                     * favoriteAdTrack : null
                     * webAdTrack : null
                     * date : 1505729177000
                     * promotion : null
                     * label : null
                     * labelList : []
                     * descriptionEditor :
                     * collected : false
                     * played : false
                     * subtitles : []
                     * lastViewTime : null
                     * playlists : null
                     * src : null
                     */

                    private String dataType;
                    private int id;
                    private String title;
                    private Object slogan;
                    private String description;
                    private ProviderBean provider;
                    private String category;
                    private AuthorBean author;
                    private CoverBean cover;
                    private String playUrl;
                    private Object thumbPlayUrl;
                    private int duration;
                    private WebUrlBean webUrl;
                    private long releaseTime;
                    private String library;
                    private ConsumptionBean consumption;
                    private Object campaign;
                    private Object waterMarks;
                    private Object adTrack;
                    private String type;
                    private String titlePgc;
                    private String descriptionPgc;
                    private Object remark;
                    private int idx;
                    private Object shareAdTrack;
                    private Object favoriteAdTrack;
                    private Object webAdTrack;
                    private long date;
                    private Object promotion;
                    private Object label;
                    private String descriptionEditor;
                    private boolean collected;
                    private boolean played;
                    private Object lastViewTime;
                    private Object playlists;
                    private Object src;
                    private List<PlayInfoBean> playInfo;
                    private List<TagsBean> tags;
                    private List<?> labelList;
                    private List<?> subtitles;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

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

                    public Object getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(Object slogan) {
                        this.slogan = slogan;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public ProviderBean getProvider() {
                        return provider;
                    }

                    public void setProvider(ProviderBean provider) {
                        this.provider = provider;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public AuthorBean getAuthor() {
                        return author;
                    }

                    public void setAuthor(AuthorBean author) {
                        this.author = author;
                    }

                    public CoverBean getCover() {
                        return cover;
                    }

                    public void setCover(CoverBean cover) {
                        this.cover = cover;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public Object getThumbPlayUrl() {
                        return thumbPlayUrl;
                    }

                    public void setThumbPlayUrl(Object thumbPlayUrl) {
                        this.thumbPlayUrl = thumbPlayUrl;
                    }

                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }

                    public WebUrlBean getWebUrl() {
                        return webUrl;
                    }

                    public void setWebUrl(WebUrlBean webUrl) {
                        this.webUrl = webUrl;
                    }

                    public long getReleaseTime() {
                        return releaseTime;
                    }

                    public void setReleaseTime(long releaseTime) {
                        this.releaseTime = releaseTime;
                    }

                    public String getLibrary() {
                        return library;
                    }

                    public void setLibrary(String library) {
                        this.library = library;
                    }

                    public ConsumptionBean getConsumption() {
                        return consumption;
                    }

                    public void setConsumption(ConsumptionBean consumption) {
                        this.consumption = consumption;
                    }

                    public Object getCampaign() {
                        return campaign;
                    }

                    public void setCampaign(Object campaign) {
                        this.campaign = campaign;
                    }

                    public Object getWaterMarks() {
                        return waterMarks;
                    }

                    public void setWaterMarks(Object waterMarks) {
                        this.waterMarks = waterMarks;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getTitlePgc() {
                        return titlePgc;
                    }

                    public void setTitlePgc(String titlePgc) {
                        this.titlePgc = titlePgc;
                    }

                    public String getDescriptionPgc() {
                        return descriptionPgc;
                    }

                    public void setDescriptionPgc(String descriptionPgc) {
                        this.descriptionPgc = descriptionPgc;
                    }

                    public Object getRemark() {
                        return remark;
                    }

                    public void setRemark(Object remark) {
                        this.remark = remark;
                    }

                    public int getIdx() {
                        return idx;
                    }

                    public void setIdx(int idx) {
                        this.idx = idx;
                    }

                    public Object getShareAdTrack() {
                        return shareAdTrack;
                    }

                    public void setShareAdTrack(Object shareAdTrack) {
                        this.shareAdTrack = shareAdTrack;
                    }

                    public Object getFavoriteAdTrack() {
                        return favoriteAdTrack;
                    }

                    public void setFavoriteAdTrack(Object favoriteAdTrack) {
                        this.favoriteAdTrack = favoriteAdTrack;
                    }

                    public Object getWebAdTrack() {
                        return webAdTrack;
                    }

                    public void setWebAdTrack(Object webAdTrack) {
                        this.webAdTrack = webAdTrack;
                    }

                    public long getDate() {
                        return date;
                    }

                    public void setDate(long date) {
                        this.date = date;
                    }

                    public Object getPromotion() {
                        return promotion;
                    }

                    public void setPromotion(Object promotion) {
                        this.promotion = promotion;
                    }

                    public Object getLabel() {
                        return label;
                    }

                    public void setLabel(Object label) {
                        this.label = label;
                    }

                    public String getDescriptionEditor() {
                        return descriptionEditor;
                    }

                    public void setDescriptionEditor(String descriptionEditor) {
                        this.descriptionEditor = descriptionEditor;
                    }

                    public boolean isCollected() {
                        return collected;
                    }

                    public void setCollected(boolean collected) {
                        this.collected = collected;
                    }

                    public boolean isPlayed() {
                        return played;
                    }

                    public void setPlayed(boolean played) {
                        this.played = played;
                    }

                    public Object getLastViewTime() {
                        return lastViewTime;
                    }

                    public void setLastViewTime(Object lastViewTime) {
                        this.lastViewTime = lastViewTime;
                    }

                    public Object getPlaylists() {
                        return playlists;
                    }

                    public void setPlaylists(Object playlists) {
                        this.playlists = playlists;
                    }

                    public Object getSrc() {
                        return src;
                    }

                    public void setSrc(Object src) {
                        this.src = src;
                    }

                    public List<PlayInfoBean> getPlayInfo() {
                        return playInfo;
                    }

                    public void setPlayInfo(List<PlayInfoBean> playInfo) {
                        this.playInfo = playInfo;
                    }

                    public List<TagsBean> getTags() {
                        return tags;
                    }

                    public void setTags(List<TagsBean> tags) {
                        this.tags = tags;
                    }

                    public List<?> getLabelList() {
                        return labelList;
                    }

                    public void setLabelList(List<?> labelList) {
                        this.labelList = labelList;
                    }

                    public List<?> getSubtitles() {
                        return subtitles;
                    }

                    public void setSubtitles(List<?> subtitles) {
                        this.subtitles = subtitles;
                    }

                    public static class ProviderBean {
                        /**
                         * name : 定制来源
                         * alias : CustomSrc
                         * icon :
                         */

                        private String name;
                        private String alias;
                        private String icon;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAlias() {
                            return alias;
                        }

                        public void setAlias(String alias) {
                            this.alias = alias;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }
                    }

                    public static class AuthorBean {
                        /**
                         * id : 550
                         * icon : http://img.kaiyanapp.com/e1ff5d3276a1cd3dfdec54db6bb83ca0.jpeg?imageMogr2/quality/60/format/jpg
                         * name : Android Authority 安卓权威
                         * description : 美国科技博客，关注安卓生态
                         * link :
                         * latestReleaseTime : 1505729177000
                         * videoNum : 31
                         * adTrack : null
                         * follow : {"itemType":"author","itemId":550,"followed":false}
                         * shield : {"itemType":"author","itemId":550,"shielded":false}
                         * approvedNotReadyVideoCount : 0
                         * ifPgc : true
                         */

                        private int id;
                        private String icon;
                        private String name;
                        private String description;
                        private String link;
                        private long latestReleaseTime;
                        private int videoNum;
                        private Object adTrack;
                        private FollowBeanX follow;
                        private ShieldBean shield;
                        private int approvedNotReadyVideoCount;
                        private boolean ifPgc;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDescription() {
                            return description;
                        }

                        public void setDescription(String description) {
                            this.description = description;
                        }

                        public String getLink() {
                            return link;
                        }

                        public void setLink(String link) {
                            this.link = link;
                        }

                        public long getLatestReleaseTime() {
                            return latestReleaseTime;
                        }

                        public void setLatestReleaseTime(long latestReleaseTime) {
                            this.latestReleaseTime = latestReleaseTime;
                        }

                        public int getVideoNum() {
                            return videoNum;
                        }

                        public void setVideoNum(int videoNum) {
                            this.videoNum = videoNum;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public FollowBeanX getFollow() {
                            return follow;
                        }

                        public void setFollow(FollowBeanX follow) {
                            this.follow = follow;
                        }

                        public ShieldBean getShield() {
                            return shield;
                        }

                        public void setShield(ShieldBean shield) {
                            this.shield = shield;
                        }

                        public int getApprovedNotReadyVideoCount() {
                            return approvedNotReadyVideoCount;
                        }

                        public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                            this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                        }

                        public boolean isIfPgc() {
                            return ifPgc;
                        }

                        public void setIfPgc(boolean ifPgc) {
                            this.ifPgc = ifPgc;
                        }

                        public static class FollowBeanX {
                            /**
                             * itemType : author
                             * itemId : 550
                             * followed : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean followed;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isFollowed() {
                                return followed;
                            }

                            public void setFollowed(boolean followed) {
                                this.followed = followed;
                            }
                        }

                        public static class ShieldBean {
                            /**
                             * itemType : author
                             * itemId : 550
                             * shielded : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean shielded;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isShielded() {
                                return shielded;
                            }

                            public void setShielded(boolean shielded) {
                                this.shielded = shielded;
                            }
                        }
                    }

                    public static class CoverBean {
                        /**
                         * feed : http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg
                         * detail : http://img.kaiyanapp.com/ec338a9c7755b35530013775132097c3.jpeg?imageMogr2/quality/60/format/jpg
                         * blurred : http://img.kaiyanapp.com/1be25fb0ff11446f5147feb7c379341a.jpeg?imageMogr2/quality/60/format/jpg
                         * sharing : null
                         * homepage : null
                         */

                        private String feed;
                        private String detail;
                        private String blurred;
                        private Object sharing;
                        private Object homepage;

                        public String getFeed() {
                            return feed;
                        }

                        public void setFeed(String feed) {
                            this.feed = feed;
                        }

                        public String getDetail() {
                            return detail;
                        }

                        public void setDetail(String detail) {
                            this.detail = detail;
                        }

                        public String getBlurred() {
                            return blurred;
                        }

                        public void setBlurred(String blurred) {
                            this.blurred = blurred;
                        }

                        public Object getSharing() {
                            return sharing;
                        }

                        public void setSharing(Object sharing) {
                            this.sharing = sharing;
                        }

                        public Object getHomepage() {
                            return homepage;
                        }

                        public void setHomepage(Object homepage) {
                            this.homepage = homepage;
                        }
                    }

                    public static class WebUrlBean {
                        /**
                         * raw : http://www.eyepetizer.net/detail.html?vid=51743
                         * forWeibo : http://wandou.im/3ocd35
                         */

                        private String raw;
                        private String forWeibo;

                        public String getRaw() {
                            return raw;
                        }

                        public void setRaw(String raw) {
                            this.raw = raw;
                        }

                        public String getForWeibo() {
                            return forWeibo;
                        }

                        public void setForWeibo(String forWeibo) {
                            this.forWeibo = forWeibo;
                        }
                    }

                    public static class ConsumptionBean {
                        /**
                         * collectionCount : 10
                         * shareCount : 10
                         * replyCount : 3
                         */

                        private int collectionCount;
                        private int shareCount;
                        private int replyCount;

                        public int getCollectionCount() {
                            return collectionCount;
                        }

                        public void setCollectionCount(int collectionCount) {
                            this.collectionCount = collectionCount;
                        }

                        public int getShareCount() {
                            return shareCount;
                        }

                        public void setShareCount(int shareCount) {
                            this.shareCount = shareCount;
                        }

                        public int getReplyCount() {
                            return replyCount;
                        }

                        public void setReplyCount(int replyCount) {
                            this.replyCount = replyCount;
                        }
                    }

                    public static class PlayInfoBean {
                        /**
                         * height : 480
                         * width : 854
                         * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun","size":47206541},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=qcloud","size":47206541},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=ucloud","size":47206541}]
                         * name : 标清
                         * type : normal
                         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun
                         */

                        private int height;
                        private int width;
                        private String name;
                        private String type;
                        private String url;
                        private List<UrlListBean> urlList;

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public List<UrlListBean> getUrlList() {
                            return urlList;
                        }

                        public void setUrlList(List<UrlListBean> urlList) {
                            this.urlList = urlList;
                        }

                        public static class UrlListBean {
                            /**
                             * name : aliyun
                             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51743&editionType=normal&source=aliyun
                             * size : 47206541
                             */

                            private String name;
                            private String url;
                            private int size;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getSize() {
                                return size;
                            }

                            public void setSize(int size) {
                                this.size = size;
                            }
                        }
                    }

                    public static class TagsBean {
                        /**
                         * id : 727
                         * name : 数码测评
                         * actionUrl : eyepetizer://tag/727/?title=%E6%95%B0%E7%A0%81%E6%B5%8B%E8%AF%84
                         * adTrack : null
                         * desc :
                         * bgPicture : http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg
                         * headerImage : http://img.kaiyanapp.com/84889e1bce0abfe21c3358b48ee1824f.jpeg?imageMogr2/quality/60/format/jpg
                         * tagRecType : IMPORTANT
                         */

                        private int id;
                        private String name;
                        private String actionUrl;
                        private Object adTrack;
                        private String desc;
                        private String bgPicture;
                        private String headerImage;
                        private String tagRecType;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getActionUrl() {
                            return actionUrl;
                        }

                        public void setActionUrl(String actionUrl) {
                            this.actionUrl = actionUrl;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public String getBgPicture() {
                            return bgPicture;
                        }

                        public void setBgPicture(String bgPicture) {
                            this.bgPicture = bgPicture;
                        }

                        public String getHeaderImage() {
                            return headerImage;
                        }

                        public void setHeaderImage(String headerImage) {
                            this.headerImage = headerImage;
                        }

                        public String getTagRecType() {
                            return tagRecType;
                        }

                        public void setTagRecType(String tagRecType) {
                            this.tagRecType = tagRecType;
                        }
                    }
                }
            }
        }
    }
}
