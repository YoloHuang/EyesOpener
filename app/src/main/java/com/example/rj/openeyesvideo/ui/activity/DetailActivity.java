package com.example.rj.openeyesvideo.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rj.openeyesvideo.R;
import com.example.rj.openeyesvideo.base.Contract.DetailContract;
import com.example.rj.openeyesvideo.base.RootActivity;
import com.example.rj.openeyesvideo.component.ImageLoader;
import com.example.rj.openeyesvideo.component.SimpleListener;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.ReplyBean;
import com.example.rj.openeyesvideo.presenter.DetailPresenter;
import com.example.rj.openeyesvideo.ui.adapter.BaseRecyclerAdapter;
import com.example.rj.openeyesvideo.ui.adapter.DetailAdapter;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class DetailActivity extends RootActivity<DetailPresenter> implements DetailContract.View {


    @BindView(R.id.vedio_player)
    StandardGSYVideoPlayer vedioPlayer;

    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    private ItemListBean itemListBean;
    String Url;
    private OrientationUtils orientationUtils;
    ImageView imageView;
    LinearLayoutManager mLinearLayoutManager;
    boolean isPlay;
    boolean isPause;
    int id;
    boolean islike=false;

    DetailAdapter mAdapter;
    private List<ItemListBean> listBeans =new ArrayList<>();



    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        getIntentData();
        initRecyclerView();
        stateLoading();
        mPresenter.getVedioData(id);
        initVedio();

    }

    private void initVedio() {
        vedioPlayer.getTitleTextView().setVisibility(View.GONE);
        vedioPlayer.getBackButton().setVisibility(View.GONE);
        orientationUtils=new OrientationUtils(this,vedioPlayer);
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder builder=new GSYVideoOptionBuilder();
        builder.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(Url)
                .setCacheWithPlay(false)
                .setStandardVideoAllCallBack(new SimpleListener(){
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        orientationUtils.setEnable(true);
                        isPlay=true;
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
                        if(orientationUtils!=null){
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if(orientationUtils!=null){
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .setGSYVideoProgressListener(new GSYVideoProgressListener() {
                    @Override
                    public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                        Log.d("hzj  ", "onProgress: ");
                    }
                })
                .build(vedioPlayer);

        vedioPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
                vedioPlayer.startWindowFullscreen(DetailActivity.this,true,true);
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter=new DetailAdapter(mContext,listBeans);
        mPresenter.isLike(id);
        mAdapter.getItemData(itemListBean);
        recyclerView.setAdapter(mAdapter);
        Log.d("hzj", "initRecyclerView: ");
        mAdapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent=new Intent();
                intent.setClass(mContext,DetailActivity.class);
                ItemListBean itemListBean=listBeans.get(id-2);
                intent.putExtra("itemListBean",itemListBean);
                mContext.startActivity(intent);
            }
        });
        mAdapter.setOnButtonClickListener(new DetailAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                switch (position){
                    case 0:
                        if(islike){
                            mPresenter.deleteLikeId(id);
                            islike=false;
                        }else {
                            mPresenter.insertLikeId(itemListBean);
                            islike=true;
                        }
                        break;
                    case 1:
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT,itemListBean.getData().getWebUrl().getRaw());
                        mContext.startActivity(Intent.createChooser(intent,"分享给……"));
                        break;
                    case 2:
                        mPresenter.getReplyData(itemListBean.getData().getId());
                        //Toast.makeText(mContext,"暂未实现",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(mContext,"暂未实现",Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        });
    }


    /**
     *获取databean中的image信息
     * 由于从likebean转过来的itemlistBean中会存在没有cover的情况，所以在此判断
     */
    private String getImageUrl(){
        if(itemListBean.getData().getCover()==null){
            return itemListBean.getData().getCoverForFeed();
        }else {
            return itemListBean.getData().getCover().getFeed();
        }
    }

    private void getIntentData() {
        itemListBean=(ItemListBean) getIntent().getSerializableExtra("itemListBean");
        Log.d("hzj", "getIntentData: itemListBean"+itemListBean.getData().getPlayUrl());
        Url=itemListBean.getData().getPlayUrl();
        //Url=getIntent().getExtras().getString("url");
        //String image=getIntent().getExtras().getString("image");
        //int id=getIntent().getExtras().getInt("itemId");
        id=itemListBean.getData().getId();
        Log.d("hzj", "getIntentData: "+Url);
        imageView=new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.load(this,getImageUrl(),imageView);
        if(!mPresenter.isRead(id)){
            mPresenter.addToHistory(itemListBean);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void showContent(List<ItemListBean> itemListBeans) {
        stateStart();
        for(ItemListBean itemListBean: itemListBeans){
            if (itemListBean.getType().equals("videoSmallCard")){
                listBeans.add(itemListBean);
            }
        }
        Log.d("hzj", "showContent: "+listBeans.size());
        mAdapter.getData(listBeans);

    }

    @Override
    public void setlike(boolean islike) {
        mAdapter.setlike(islike);
        this.islike=islike;
    }

    @Override
    public void showReply(ReplyBean replyBean) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    private GSYVideoPlayer getPlayer(){
        if(vedioPlayer.getFullWindowPlayer()!=null){
            return vedioPlayer.getFullWindowPlayer();
        }
        return vedioPlayer;
    }


//    @Override
//    public void onBackPresse() {
//        Log.d("hzj", "onBackPressedSupport: ");
//        if (orientationUtils!=null){
//            Log.d("hzj", "onBackPressedSupport: orientationUtils");
//            orientationUtils.backToProtVideo();
//        }
//        if (StandardGSYVideoPlayer.backFromWindowFull(this)){
//            return;
//        }
//        super.onBackPressed();
//    }

    @Override
    public void onBackPressedSupport() {
        Log.d("hzj", "onBackPressedSupport: ");
        if (orientationUtils!=null){
            Log.d("hzj", "onBackPressedSupport: orientationUtils");
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)){
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        getPlayer().onVideoPause();
        super.onPause();
        isPause=true;
    }

    @Override
    protected void onResume() {
        getPlayer().onVideoResume();
        super.onResume();
        isPause=false;
    }

    @Override
    protected void onDestroy() {

        if(isPlay){
            getPlayer().release();
        }
        if(orientationUtils!=null){
            orientationUtils.releaseListener();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(isPlay&& !isPause){
            vedioPlayer.onConfigurationChanged(this,newConfig,orientationUtils);
        }
    }

    @Override
    public void showErrorMsg(String s) {

    }
}
