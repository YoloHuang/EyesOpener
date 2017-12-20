package com.example.rj.openeyesvideo.APP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.rj.openeyesvideo.di.component.AppComponent;
import com.example.rj.openeyesvideo.di.component.DaggerAppComponent;
import com.example.rj.openeyesvideo.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rj on 2017/12/15.
 */

public class App extends Application {

    private static App app;
    private Set<Activity> activities;
    public static AppComponent appComponent;

    public static synchronized App getApp(){
        if(app==null){
            app=new App();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;

    }

    public static AppComponent getAppComponent(){
        if(appComponent==null){
            appComponent=DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
        return appComponent;
    }

    public void addActivity(Activity activity){
        if(activities==null){
            activities=new HashSet<>();
        }
        activities.add(activity);
    }
    public void removeActivity(Activity activity){
        if(activities!=null){
            activities.remove(activity);
        }
    }
    public void exitApp(){
        if(activities!=null){
            synchronized (activities){
                for(Activity activity: activities){
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
