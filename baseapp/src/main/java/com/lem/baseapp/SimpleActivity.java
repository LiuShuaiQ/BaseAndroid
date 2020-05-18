package com.lem.baseapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;

public abstract class SimpleActivity extends AppCompatActivity implements IView {
    protected Activity mContext;

    private ProgressDialog mProgressDialog = null;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mContext = this;
        beforeSetContentView();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initData();
    }

    protected void initProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void beforeSetContentView() {

    }

    protected void initData() {

    }

    @Override
    public Context getActivityContext() {
        return mContext;
    }

    @Override
    public void showLoading(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void setBackToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
