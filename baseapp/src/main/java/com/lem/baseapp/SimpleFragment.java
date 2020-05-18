package com.lem.baseapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleFragment extends Fragment implements IView {
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnBinder;

    private ProgressDialog mProgressDialog = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResId(), null);
        }
        mUnBinder = ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    protected void initProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroyView();
    }


    @Override
    public void showLoading(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getActivityContext() {
        return mContext;
    }
}
