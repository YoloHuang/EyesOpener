package com.example.hzj.EyeOpener.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.Contract.DetailContract;
import com.example.hzj.EyeOpener.base.RootActivity;
import com.example.hzj.EyeOpener.component.ImageLoader;
import com.example.hzj.EyeOpener.component.SimpleListener;
import com.example.hzj.EyeOpener.model.bean.ItemListBean;
import com.example.hzj.EyeOpener.model.bean.ReplyBean;
import com.example.hzj.EyeOpener.presenter.DetailPresenter;
import com.example.hzj.EyeOpener.ui.adapter.DetailAdapter;
import com.example.hzj.EyeOpener.ui.view.ReplyView;
import com.example.hzj.EyeOpener.util.SystemUtil;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;

public class DetailActivity extends RootActivity<DetailPresenter> implements DetailContract.View {


    @BindView(R.id.vedio_player)
    StandardGSYVideoPlayer vedioPlayer;

    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    String Url;
    ImageView imageView;
    LinearLayoutManager mLinearLayoutManager;
    boolean isPlay;
    boolean isPause;
    int id;
    boolean islike = false;
    boolean isloading = false;
    boolean notShowPlayTip;
    boolean notShowDownloadTip;
    DetailAdapter mAdapter;
    //评论相关
    ReplyView mReplyView;
    RelativeLayout.LayoutParams mLayoutParams;
    RelativeLayout root;
    Stack<ReplyView> replyViews = new Stack<ReplyView>();
    private ItemListBean itemListBean;
    private OrientationUtils orientationUtils;
    private List<ItemListBean> listBeans = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        itemListBean = (ItemListBean) getIntent().getSerializableExtra("itemListBean");
        getIntentData();
        initRecyclerView();
        stateLoading();
        notShowPlayTip = mPresenter.getPlaySetting();
        notShowDownloadTip = mPresenter.getDownloadSetting();
        mPresenter.getVedioData(id);
        initVedio();
        root = (RelativeLayout) findViewById(R.id.detail_root);
    }

    /**
     * 初始化vedio
     */
    private void initVedio() {
        vedioPlayer.getTitleTextView().setVisibility(View.GONE);
        vedioPlayer.getBackButton().setVisibility(View.GONE);
        orientationUtils = new OrientationUtils(this, vedioPlayer);
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder builder = new GSYVideoOptionBuilder();
        builder.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(true)
                .setRotateWithSystem(false)
                .setThumbPlay(true)
                .setLockLand(true)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setNeedShowWifiTip(!notShowPlayTip)
                .setSeekRatio(1)
                .setUrl(Url)
                .setCacheWithPlay(false)
                .setStandardVideoAllCallBack(new SimpleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        Log.d("hzj", "onEnterFullscreen: ");
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                        Log.d("hzj", "onAutoComplete: ");
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Log.d("hzj", "onQuitFullscreen: ");
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .setGSYVideoProgressListener(new GSYVideoProgressListener() {
                    @Override
                    public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                    }
                })
                .build(vedioPlayer);

        vedioPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
                vedioPlayer.startWindowFullscreen(DetailActivity.this, true, true);
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DetailAdapter(mContext, listBeans);
        mPresenter.isLike(id);
        mAdapter.getItemData(itemListBean);
        recyclerView.setAdapter(mAdapter);
        //为相关视频推荐设置监听事件，会重新加载整个detailActivity
        mAdapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                itemListBean = listBeans.get(id - 2);
                stateLoading();
                getIntentData();
                mAdapter.getItemData(itemListBean);
                mPresenter.isLike(id);
                listBeans.clear();
                initVedio();
                mPresenter.getVedioData(itemListBean.getData().getId());
            }
        });
        //为button设置点击事件
        mAdapter.setOnButtonClickListener(new DetailAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                switch (position) {
                    case 0:
                        if (islike) {
                            mPresenter.deleteLikeId(id);
                            islike = false;
                        } else {
                            mPresenter.insertLikeId(itemListBean);
                            islike = true;
                        }
                        break;
                    case 1:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, itemListBean.getData().getWebUrl().getRaw());
                        mContext.startActivity(Intent.createChooser(intent, "分享给……"));
                        break;
                    case 2:
                        mPresenter.getReplyData(itemListBean.getData().getId());
                        break;
                    case 3:
                        if (SystemUtil.isWifiConnected() || notShowDownloadTip) {
                            mPresenter.download(Url, itemListBean);
                        } else {
                            showDownloadDialog();
                        }
                        break;
                }


            }
        });
    }


    /**
     * 获取databean中的image信息
     * 由于从likebean转过来的itemlistBean中会存在没有cover的情况，所以在此判断
     */
    private String getImageUrl() {
        if (itemListBean.getData().getCover() == null) {
            return itemListBean.getData().getCoverForFeed();
        } else {
            return itemListBean.getData().getCover().getFeed();
        }
    }

    /**
     * 获取启动此activity时，intent所传递过来的数据
     */
    private void getIntentData() {
        Url = itemListBean.getData().getPlayUrl();
        id = itemListBean.getData().getId();
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.load(this, getImageUrl(), imageView);
        if (!mPresenter.isRead(id)) {
            mPresenter.addToHistory(itemListBean);
        } else {
            mPresenter.deleteReadId(id);
            mPresenter.addToHistory(itemListBean);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    /**
     * 显示数据
     *
     * @param itemListBeans
     */
    @Override
    public void showContent(List<ItemListBean> itemListBeans) {
        stateStart();
        for (ItemListBean itemListBean : itemListBeans) {
            if (itemListBean.getType().equals("videoSmallCard")) {
                listBeans.add(itemListBean);
            }
        }
        mAdapter.getData(listBeans);

    }

    /**
     * 根据是否喜欢设置界面icon
     *
     * @param islike
     */
    @Override
    public void setlike(boolean islike) {
        mAdapter.setlike(islike);
        this.islike = islike;
    }

    /**
     * 显示评论，覆盖之前的recycylerview
     *
     * @param replyBean
     */
    @Override
    public void showReply(final ReplyBean replyBean) {
        mReplyView = new ReplyView(mContext);
        mLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mLayoutParams.addRule(RelativeLayout.BELOW, R.id.vedio_player);
        mReplyView.setLayoutParams(mLayoutParams);
        mReplyView.getData(replyBean);
        root.addView(mReplyView);
        replyViews.push(mReplyView);
        mReplyView.replyClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeReply();
            }
        });
        mReplyView.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon = mReplyView.linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mReplyView.linearLayoutManager.getItemCount();
                if (lastItemPositon > totalPotions - 4 && dy > 0 && totalPotions < replyBean.getTotal()) {
                    if (!isloading) {
                        isloading = true;
                        mPresenter.getMoreReplyData(itemListBean.getData().getId());
                    }
                }
            }
        });

    }

    /**
     * 上拉后显示更多数据
     *
     * @param replyBean
     */
    @Override
    public void showMoreReply(ReplyBean replyBean) {
        mReplyView.getMoreData(replyBean);
        isloading = false;
    }

    @Override
    public void showDownload() {
        Toast.makeText(mContext, "下载中~可进入我的缓存查看进度", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIsDownload() {
        Toast.makeText(mContext, "已经在下载啦~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHadDownload() {
        Toast.makeText(mContext, "已经下载过啦~", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示是否使用流量下载的dialog
     */
    @Override
    public void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("确定用流量下载吗？")
                .setMessage("当前未连接WIFI，确定要用流量下载吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.setDownloadSetting(true);
                        mPresenter.download(Url, itemListBean);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 关闭评论
     *
     * @return
     */
    private boolean closeReply() {
        if (replyViews.size() == 0) {
            return false;
        }
        root.removeView(mReplyView);
        replyViews.pop();
        return true;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    /**
     * 根据是否全屏状态来获取GSYVideoPlayer
     *
     * @return
     */
    private GSYVideoPlayer getPlayer() {
        if (vedioPlayer.getFullWindowPlayer() != null) {
            return vedioPlayer.getFullWindowPlayer();
        }
        return vedioPlayer;
    }

    /**
     * 根据不同状态，处理返回事件
     */
    @Override
    public void onBackPressedSupport() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        if (closeReply()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        getPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getPlayer().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {

        if (isPlay) {
            getPlayer().release();
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isPlay && !isPause) {
            vedioPlayer.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }

    @Override
    public void showErrorMsg(String s) {

    }
}
