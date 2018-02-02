package com.example.hzj.EyeOpener.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzj.EyeOpener.APP.Constants;
import com.example.hzj.EyeOpener.R;
import com.example.hzj.EyeOpener.base.BaseActivity;
import com.example.hzj.EyeOpener.base.Contract.SettingContract;
import com.example.hzj.EyeOpener.component.Acache;
import com.example.hzj.EyeOpener.presenter.SettingPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.tv_downloadsetting_false)
    TextView downloadSettingFalse;
    @BindView(R.id.tv_downloadsetting_true)
    TextView downloadSettingTrue;
    @BindView(R.id.tv_playsetting_false)
    TextView playSettingFalse;
    @BindView(R.id.tv_playsetting_true)
    TextView playSettingTrue;
    @BindView(R.id.tv_cleancache)
    TextView cleanCache;
    @BindView(R.id.tv_search_update)
    TextView searchUpdate;
    @BindView(R.id.tv_cache)
    TextView cache;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.ic_toolbar_search)
    ImageView search;

    boolean downloadSetting;
    boolean playSetting;
    private File cacheFile;

    @Override
    protected void initEventAndData() {
        cacheFile = new File(Constants.PATH_CACHE);
        downloadSetting = mPresenter.getDownloadSetting();
        playSetting = mPresenter.getPlaySetting();
        Log.d("hzj", "initEventAndData: " + downloadSetting + playSetting);
        cache.setText(Acache.getCacheSize(cacheFile));
        title.setText("设置");
        search.setVisibility(View.GONE);
        setSelected();
    }

    void setSelected() {
        downloadSettingTrue.setSelected(downloadSetting);
        downloadSettingFalse.setSelected(!downloadSetting);
        playSettingFalse.setSelected(!playSetting);
        playSettingTrue.setSelected(playSetting);
    }

    @OnClick(R.id.tv_downloadsetting_false)
    void setDownloadSettingFalse() {
        if (downloadSetting) {
            downloadSetting = false;
            mPresenter.setDownloadSetting(downloadSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_downloadsetting_true)
    void setDownloadSettingTrue() {
        if (!downloadSetting) {
            downloadSetting = true;
            mPresenter.setDownloadSetting(downloadSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_playsetting_true)
    void setPlaySettingTrue() {
        if (!playSetting) {
            playSetting = true;
            mPresenter.setPlaySetting(playSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_playsetting_false)
    void setPlaySettingFalse() {
        if (playSetting) {
            playSetting = false;
            mPresenter.setPlaySetting(playSetting);
            setSelected();
        }
    }

    /**
     * 清理缓存
     */
    @OnClick(R.id.tv_cleancache)
    void cleanCache() {
        Acache.deleteDir(cacheFile);
        cache.setText(Acache.getCacheSize(cacheFile));
        showCleanToast();
    }

    @OnClick(R.id.tv_search_update)
    void checkUpdate() {
        Toast.makeText(mContext, "当前已是最新版本~", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void showCleanToast() {
        Toast.makeText(mContext, "缓存已清除~", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
