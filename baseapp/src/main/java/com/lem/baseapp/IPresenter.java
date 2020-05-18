package com.lem.baseapp;

/**
 * Presenter的抽象接口
 *
 * @param <T>
 */
public interface IPresenter<T extends IView> {
    void attachView(T view);

    void detachView();
}
