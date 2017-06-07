package com.sizu.mingteng.my_xianglekang.global.rx;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2017/6/7.
 */

interface RetrofitonNext<T> {
    void onNext(Observable<T> observable);
}
