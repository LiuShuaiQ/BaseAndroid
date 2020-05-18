package com.lem.baseapp;

import android.app.Application;
import android.content.Context;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.lem.baseapp.log.LogUtil;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        LogUtil.isDebug = BuildConfig.DEBUG;
        LogUtil.isRecordCache = BuildConfig.DEBUG;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

}
