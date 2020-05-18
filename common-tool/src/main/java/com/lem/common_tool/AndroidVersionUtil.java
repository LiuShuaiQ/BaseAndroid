package com.lem.common_tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.lem.baseapp.log.LogUtil;

public class AndroidVersionUtil {

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtil.e("Exception");
        }
        return versionName;
    }

    /**
     * 获取应用程序版本号
     */
    public static int getAppVersionCode(Context context) {
        int code = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            code = pi.versionCode;
        } catch (Exception e) {
            LogUtil.e("Exception");
        }
        return code;
    }

}
