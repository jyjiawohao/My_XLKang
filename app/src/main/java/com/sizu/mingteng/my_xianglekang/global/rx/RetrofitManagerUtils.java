package com.sizu.mingteng.my_xianglekang.global.rx;

import android.content.Context;

import com.sizu.mingteng.my_xianglekang.ApiService;
import com.sizu.mingteng.my_xianglekang.App;
import com.sizu.mingteng.my_xianglekang.network.RetrofitManager;
import com.sizu.mingteng.my_xianglekang.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/6/7.
 */

public class RetrofitManagerUtils implements ProgressCancelListener,RetrofitonNext {
    private ProgressDialogHandler mProgressDialogHandler;
    private final ApiService mApiService;
    private static RetrofitManagerUtils sInstace;
    private SubscriberOnNextListener mSubscriberOnNextListener;

    private RetrofitManagerUtils(SubscriberOnNextListener mSubscriberOnNextListener,Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
        RetrofitManager.init(App.getContext());
        RetrofitManager instance = RetrofitManager.getInstance();
        mApiService = instance.newRetrofit(ApiService.BASEURL).create(ApiService.class);
    }

 /*   public static RetrofitManagerUtils getInstance() {
        if (sInstace==null){
            sInstace = InnerHolder.INSTACE;
        }
        return sInstace;
    }
    private static class InnerHolder {
        private static RetrofitManagerUtils INSTACE = new RetrofitManagerUtils(SubscriberOnNextListener mSubscriberOnNextListener,App.getContext());
    }*/

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
     *  Observable<MovieBean> movie = apiService.getTheatersMovie();
         movie.subscribeOn(Schedulers.io())
         mApiService 获取
     * @param observable
     * @param <T>
     */
    public <T> void toSubscribeObserver(Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
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
                });
    }


    @Override
    public void onCancelProgress() {

    }

    @Override
    public void onNext(Observable observable) {
    }
}
