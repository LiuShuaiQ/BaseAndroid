package com.lem.baseapp;

import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * author: liushuai.
 * date:   18-10-8 上午11:15
 * mail: 1462828919@qq.com
 */
public class BaseModel implements Model {

    /**
     * rx wrapper for single
     */
    protected <T> Single<T> rxWrapperForSingle(final Callable<T> callable) {
        return Single.defer(new Callable<Single<? extends T>>() {
            @Override
            public Single<? extends T> call() {
                return Single.create(new SingleOnSubscribe<T>() {
                    @Override
                    public void subscribe(SingleEmitter<T> emitter) throws Exception {
                        emitter.onSuccess(callable.call());
                    }
                });
            }
        });
    }

    /**
     * rx wrapper for flowable
     */
    protected <T> Flowable<T> rxWrapperForFlowable(final Callable<T> callable) {
        return Flowable.defer(new Callable<Flowable<? extends T>>() {
            @Override
            public Flowable<? extends T> call() {
                return Flowable.create(new FlowableOnSubscribe<T>() {
                    @Override
                    public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                        emitter.onNext(callable.call());
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER);
            }
        });
    }

    /**
     * rx wrapper for observable
     */
    protected <T> Observable<T> rxWrapperForObservable(final Callable<T> callable) {
        return Observable.defer(new Callable<Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call() {
                return Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        emitter.onNext(callable.call());
                        emitter.onComplete();
                    }
                });
            }
        });
    }

    /**
     * rx wrapper for observable
     */
    protected Completable rxWrapperForCompletable(final Callable<Boolean> callable) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (callable.call()) {
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("execute rxWrapperForCompletable error!!"));
                }
            }
        });
    }
}
