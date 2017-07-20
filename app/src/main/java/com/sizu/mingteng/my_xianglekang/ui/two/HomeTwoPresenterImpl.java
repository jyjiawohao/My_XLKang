package com.sizu.mingteng.my_xianglekang.ui.two;

import com.sizu.mingteng.my_xianglekang.rxjava.RetrofitHelper;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;
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
 * Created by lenovo on 2017/7/14.
 */

public class HomeTwoPresenterImpl implements HomeTwoPresenter {
    private HomeTwoView mHomeTwoView;
    private List<HomeTwoBean.ResultsBean> mBeanList;

    public HomeTwoPresenterImpl(HomeTwoView homeTwoView) {
        mHomeTwoView = homeTwoView;
        mBeanList = new ArrayList<>();
    }

    @Override
    public void InitNetWork(String all, int number, int page) {
        Observable<HomeTwoBean> movieDetail = RetrofitHelper.getGanKApiService().getAll(all, number, page);
        movieDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeTwoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(@NonNull HomeTwoBean response) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onNext: " + response);

                        List<HomeTwoBean.ResultsBean> results = response.getResults();
                        if (response != null)
                            mBeanList.addAll(results);
                        mHomeTwoView.Success(mBeanList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onError: " + e.getLocalizedMessage());
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

                    }

                    @Override
                    public void onComplete() {
                        MyLogger.e("HomeOneViewPresenterImpl", "onComplete: ");

                    }
                });
    }
}
