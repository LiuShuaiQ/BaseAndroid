package com.lem.baseapp;

import androidx.annotation.NonNull;

public abstract class BaseActivity<P extends IPresenter> extends SimpleActivity {

    protected P mPresenter;

    @Override
    protected void initData() {
        super.initData();
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @NonNull
    protected abstract P getPresenter();
}
