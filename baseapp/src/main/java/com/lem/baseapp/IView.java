package com.lem.baseapp;

import android.content.Context;

/**
 * View抽象接口
 */
public interface IView {
    Context getActivityContext();

    void showLoading(String msg);

    void hideLoading();

    void showMsg(String msg);
}
