package com.sizu.mingteng.my_xianglekang;

import com.sizu.mingteng.my_xianglekang.bean.GanIoTypeBean;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.bean.MovieDetailBean;
import com.sizu.mingteng.my_xianglekang.network.BaseBean;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2017/5/27.
 */

public interface ApiService {

    //region @description API 定义相关
    String BASEURL = "http://api.douban.com/";

    /**
     * 首页轮播图
     */
    @GET("ting?from=android&version=5.8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14")
    Observable<BaseBean<Object>> getFrontpage();

    @GET("main/mainSearch/{token}")
    Observable<BaseBean<Object>> getHome(@Path("token") String token);

    @GET("v2/movie/in_theaters")
    Observable<MovieBean> getTheatersMovie();

    @GET("v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") int id);
    ///category/Android/count/10/page/1

    @GET("category/{all}/count/{number}/page/{pageNumber}")
    Observable<HomeTwoBean> getAndroid(@Path("all") String all, @Path("number") int number, @Path("pageNumber") int pageNumber);

    @GET("data/{all}/{number}/{pageNumber}")
    Observable<HomeTwoBean> getAll(@Path("all") String all, @Path("number") int number, @Path("pageNumber") int pageNumber);

    @GET("data/{all}/{number}/{pageNumber}")
    Observable<GanIoTypeBean> getAllType(@Path("all") String all, @Path("number") int number, @Path("pageNumber") int pageNumber);
}
