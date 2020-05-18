package com.lem.baseapp.log;

import android.util.Log;

import java.io.IOException;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuShuai
 */
public class LogUtil {
    public static final String TAG = "LogUtil";
    public static boolean isDebug = true;
    public static boolean isRecordCache = true;

    private static final String formatLogString = "Log---------------->";

    public static void d(String log) {
        if (!isDebug) {
            return;
        }
        if (isRecordCache) {
            try {
                LogCacheFile.getInstance()
                        .rxLogD(log)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action() {
                            @Override
                            public void run() {
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, formatLogString + log);
    }

    public static void e(String log) {
        if (!isDebug) {
            return;
        }
        if (isRecordCache) {
            try {
                LogCacheFile.getInstance()
                        .rxLogE(log)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action() {
                            @Override
                            public void run() {
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e(TAG, formatLogString + log);
    }

    public static void http(String log) {
        if (!isDebug) {
            return;
        }
        if (isRecordCache) {
            try {
                LogCacheFile.getInstance()
                        .rxLogHttp(log)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action() {
                            @Override
                            public void run() {
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e(TAG, formatLogString + log);
    }

    public static void printExp(Throwable e) {
        if (!isDebug) {
            return;
        }
        e.printStackTrace();
        if (e == null) {
            return;
        }
        if (isRecordCache) {
            try {
                StringBuilder logString = new StringBuilder();
                logString
                        .append("发生异常:").append(e.getMessage()).append("\n")
                        .append("cash by: ").append(e.getCause()).append("\n");
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    logString.append(stackTraceElement.toString()).append("\n");
                }
                LogCacheFile.getInstance()
                        .rxLogE(logString.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action() {
                            @Override
                            public void run() {
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        e("发生异常:" + e.getMessage());
        e.printStackTrace();
    }
}
