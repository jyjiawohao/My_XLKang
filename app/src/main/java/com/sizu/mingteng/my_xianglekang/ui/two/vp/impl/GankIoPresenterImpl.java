package com.sizu.mingteng.my_xianglekang.ui.two.vp.impl;

import com.sizu.mingteng.my_xianglekang.bean.GanIoTypeBean;
import com.sizu.mingteng.my_xianglekang.rxjava.RetrofitHelper;
import com.sizu.mingteng.my_xianglekang.ui.two.vp.GankIoView;
import com.sizu.mingteng.my_xianglekang.ui.two.vp.presenter.GankIoPresenter;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;
import com.sizu.mingteng.my_xianglekang.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by lenovo on 2017/7/20.
 */

public class GankIoPresenterImpl implements GankIoPresenter {

    private GankIoView mGankIoView;
    private List<GanIoTypeBean.ResultsBean> mBeanList;

    public GankIoPresenterImpl(GankIoView gankIoView) {
        mGankIoView = gankIoView;
        mBeanList = new ArrayList<>();
    }

    @Override
    public void InitNetWork(String url, int number, final int page) {
        Observable<GanIoTypeBean> movieDetail = RetrofitHelper.getGanKApiService().getAllType(url, number, page);
        movieDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GanIoTypeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        MyLogger.e("GankIoPresenterImpl", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(@NonNull GanIoTypeBean response) {
                        MyLogger.e("GankIoPresenterImpl", "onNext: " + response);
                        List<GanIoTypeBean.ResultsBean> results = response.getResults();
                      /*  if (response != null) {
                            if (page == 1)
                                mBeanList.clear();
                            mBeanList.addAll(results);
                        }*/
                        mGankIoView.responseSuccess(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MyLogger.e("GankIoPresenterImpl", "onError: " + e.getLocalizedMessage());
                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                ToastUtils.showToast("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            ToastUtils.showToast("网络中断，请检查您的网络状态");
                        } else if (e instanceof SocketTimeoutException) {
                            ToastUtils.showToast("网络连接超时");
                        } else {
                            // callBack.onFaild("发生未知错误" + e.getMessage());
                            ToastUtils.showToast("error:" + e.getMessage());
                        }

                        mGankIoView.responseError(e);
                    }

                    @Override
                    public void onComplete() {
                        MyLogger.e("GankIoPresenterImpl", "onComplete: ");

                    }
                });
    }
}
