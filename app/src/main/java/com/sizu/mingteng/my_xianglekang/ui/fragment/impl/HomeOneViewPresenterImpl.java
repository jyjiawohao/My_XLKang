package com.sizu.mingteng.my_xianglekang.ui.fragment.impl;

import com.sizu.mingteng.my_xianglekang.ApiService;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.global.rx.SubscriberOnNextListener;
import com.sizu.mingteng.my_xianglekang.network.RetrofitManager;
import com.sizu.mingteng.my_xianglekang.ui.one.HomeOneView;
import com.sizu.mingteng.my_xianglekang.ui.fragment.persenter.HomeOneViewPresenter;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by lenovo on 2017/5/27.
 */

public class HomeOneViewPresenterImpl implements HomeOneViewPresenter {
    private HomeOneView mHomeOneView;

    public HomeOneViewPresenterImpl(HomeOneView homeOneView) {
        mHomeOneView = homeOneView;
    }

    /**
     * 轮播图网络请求
     */

    @Override
    public void bannerInitNetWork() {
        //RetrofitManager.init(App.getContext());
        RetrofitManager instance = RetrofitManager.getInstance();
        ApiService apiService = instance.newRetrofit(ApiService.BASEURL).create(ApiService.class);
        Observable<MovieBean> movie = apiService.getTheatersMovie();
        movie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieBean>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onSubscribe: " + d);
                        // mHomeOneView.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull MovieBean movieBean) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onNext: " + movieBean);
                        List<MovieBean.SubjectsBean> subjects = movieBean.getSubjects();
                        mHomeOneView.onSuccess(movieBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MyLogger.e("HomeOneViewPresenterImpl", "onError: " + e.getLocalizedMessage());
                        mHomeOneView.onError(e);

                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                // callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            //callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            // callBack.onFaild("网络连接超时!!");
                        } else {
                            // callBack.onFaild("发生未知错误" + e.getMessage());
                            // KLog.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        MyLogger.e("HomeOneViewPresenterImpl", "onComplete: ");
                        mHomeOneView.onComplete();
                    }
                });

        SubscriberOnNextListener<MovieBean> subscriberOnNextListener = new SubscriberOnNextListener<MovieBean>() {
            @Override
            public void onNext(MovieBean movieBean) {

            }
        };
    }
}
