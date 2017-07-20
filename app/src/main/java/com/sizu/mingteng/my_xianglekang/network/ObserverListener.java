package com.sizu.mingteng.my_xianglekang.network;

/**
 * Created by lenovo on 2017/6/8.
 */

public interface ObserverListener<T> {
    void onNext(T t);
    void onSubscribe();
    void onError(Throwable e);
    void onComplete();
}
