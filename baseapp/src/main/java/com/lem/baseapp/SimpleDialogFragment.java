package com.lem.baseapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lem.baseapp.log.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: liushuai.
 * date:   18-9-6 上午11:49
 * mail: 1462828919@qq.com
 */

public abstract class SimpleDialogFragment extends DialogFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        initEventAndData();
        return mView;
    }

    @Override
    public void onStart() {
        try {
            super.onStart();
            setBackgroundTransparent();
        } catch (RuntimeException e) {
            // no reason for this bug :
            // Fatal Exception: java.lang.RuntimeException
            //Could not read input channel file descriptors from parcel.
            //cn.lezhi.speedtest.base.SimpleDialogFragment.onStart
            LogUtil.printExp(e);
        }
    }

    @Override
    public void onDestroyView() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroyView();
    }

    protected void setBackgroundTransparent() {
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    /**
     * fuck the bug: Can not perform this action after onSaveInstanceState
     */
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag).addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            LogUtil.e(e.getMessage());
        }
    }

    boolean mIsStateAlreadySaved = false;
    boolean mPendingShowDialog = false;

    @Override
    public void onResume() {
        onResumeFragments();
        super.onResume();
    }

    public void onResumeFragments() {
        mIsStateAlreadySaved = false;
        if (mPendingShowDialog) {
            mPendingShowDialog = false;
            showSnoozeDialog();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsStateAlreadySaved = true;
    }

    private void showSnoozeDialog() {
        if (mIsStateAlreadySaved) {
            mPendingShowDialog = true;
        } else {
            FragmentManager fm = getFragmentManager();
            this.show(fm, "SimpleDialogFragment");
        }
    }
}
