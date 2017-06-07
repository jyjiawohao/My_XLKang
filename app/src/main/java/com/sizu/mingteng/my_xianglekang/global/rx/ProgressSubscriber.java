package com.sizu.mingteng.my_xianglekang.global.rx;

import android.content.Context;

import com.sizu.mingteng.my_xianglekang.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/6/7.
 */

public class ProgressSubscriber <T> implements Observer<T>, ProgressCancelListener{
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    /**
     * 显示对话框
     */
    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 隐藏对话框
     */
    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }



    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        ToastUtils.showToast("Get Top Movie Completed");
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else {
            ToastUtils.showToast("error:" + e.getMessage());
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     *
     * 原来它在finally里自动取消了订阅!!
        同样，在出现error时，也会自动取消订阅。
     */
    @Override
    public void onCancelProgress() {
        /*if (!this.isUnsubscribed()) {
            this.unsubscribe();*/
    }
}


