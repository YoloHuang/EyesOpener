package com.example.hzj.EyeOpener.APP;

import android.app.Activity;
import android.app.Application;

import com.example.hzj.EyeOpener.di.component.AppComponent;
import com.example.hzj.EyeOpener.di.component.DaggerAppComponent;
import com.example.hzj.EyeOpener.di.module.AppModule;
import com.example.hzj.EyeOpener.ui.activity.DetailActivity;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;

/**
 * Created by hzj on 2017/12/15.
 * 总Application对整个APP进行归纳
 */

public class App extends Application {

    public static AppComponent appComponent;
    private static App app;
    private static Set<Activity> activities;

    public static synchronized App getApp() {
        return app;
    }

    /**
     * 提供appComponent
     *
     * @return
     */
    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
        return appComponent;
    }

    public static void exitApp() {
        if (activities != null) {
            synchronized (activities) {
                for (Activity activity : activities) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化Realm
        Realm.init(getApplicationContext());
        //初始化BlockCanary，LeakCanary
        BlockCanary.install(this, new BlockCanaryContext());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        } else {
            LeakCanary.install(this);
        }
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new HashSet<>();
        }
        if (activity instanceof DetailActivity) {
            for (Activity activity1 : activities) {
                if (activity1 instanceof DetailActivity) {
                    removeActivity(activity1);
                    activity1.finish();
                }
            }
        }
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activities != null) {
            activities.remove(activity);
        }
    }
}
