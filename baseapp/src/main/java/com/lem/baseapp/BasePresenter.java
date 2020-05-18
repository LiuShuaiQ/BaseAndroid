package com.lem.baseapp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;
    protected boolean isAttach = false;

    private List<Disposable> mDisposableList;

    @Override
    public void attachView(V view) {
        this.mView = view;
        isAttach = true;
        mDisposableList = new ArrayList<>();
    }

    @Override
    public void detachView() {
        disposeAll();
        isAttach = false;
        this.mView = null;
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        mDisposableList.add(disposable);
    }

    public void disposeAll() {
        for (Disposable disposable : mDisposableList) {
            if (disposable == null || disposable.isDisposed()) {
                continue;
            }
            disposable.dispose();
        }
    }
}
