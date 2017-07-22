package com.sizu.mingteng.my_xianglekang.ui.two.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.GanIoTypeBean;
import com.sizu.mingteng.my_xianglekang.databinding.ActivityGankIoBinding;
import com.sizu.mingteng.my_xianglekang.global.UriUtil;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.GanKIoAdapter;
import com.sizu.mingteng.my_xianglekang.ui.two.vp.GankIoView;
import com.sizu.mingteng.my_xianglekang.ui.two.vp.impl.GankIoPresenterImpl;
import com.sizu.mingteng.my_xianglekang.ui.two.vp.presenter.GankIoPresenter;
import com.sizu.mingteng.my_xianglekang.util.CommonUtil;
import com.sizu.mingteng.my_xianglekang.util.SpacesItemDecoration;
import com.sizu.mingteng.my_xianglekang.xbase.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/20.
 * <p>
 * android  ios  福利 共同使用的类
 */

public class GankIoActivity extends BaseActivity<ActivityGankIoBinding> implements GankIoView, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String TYPE = "type";
    private GankIoPresenter mGankIoPresenter;
    private RecyclerView mRecycleview;
    private SwipeRefreshLayout mSwipeRefresh;
    private GanKIoAdapter mAdapter;
    private List<GanIoTypeBean.ResultsBean> mBeanList11;
    private String mStringExtra;

    private int number = 20; //数量
    private int mPage = 1;  //页数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_io);
        getViewLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void loadData() {
        number = 20;
        mPage = 1;
        mGankIoPresenter.InitNetWork(mStringExtra, number, mPage); //从新加载  请求网络
    }

    protected void getViewLayout() {
        mRecycleview = bindingView.includeRecyclerLayout.recycleview;
        mSwipeRefresh = bindingView.includeRecyclerLayout.swipeRefresh;

        mBeanList11 = new ArrayList<>();
        initAdapter();
        AdapterScrollListener(); //上拉加载


        mStringExtra = getIntent().getStringExtra(TYPE);
        setTitle(mStringExtra);
        mGankIoPresenter = new GankIoPresenterImpl(this);
        mGankIoPresenter.InitNetWork(mStringExtra, number, mPage); //请求网络

        /**
         * 刷新数据
         */
        mSwipeRefresh.setColorSchemeColors(CommonUtil.getColor(R.color.red));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mGankIoPresenter.InitNetWork(mStringExtra, number, mPage); //下拉刷新的时候 请求网络
            }
        });
    }

    public static void getCallingIntent(Context context, String s) {
        Intent intent = new Intent(context, GankIoActivity.class);
        intent.putExtra(TYPE, s);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade_entry, R.anim.hold);
    }

    //关闭  覆盖
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.fade_exit);
    }

    /**
     * 网络请求成功获取的数据
     *
     * @param beanList
     */
    private boolean isFirst = true; //记录是否显示成功视图

    @Override
    public void responseSuccess(List<GanIoTypeBean.ResultsBean> beanList) {

        if (mPage == 1) { //下拉刷新的时候判断 是否是 有数据的
            if (beanList == null && beanList.size() == 0) {
                showEmpty(); //暂无数据 视图
                isFirst = true;  //设置第一次加载
                return;
            }
        }
        if (isFirst) {
            showContentView();//显示 成功的视图
            isFirst = false; //记录是否再次显示成功的视图  如果显示一次就不在显示了
        }
        if (beanList.size() == 0) {
            mAdapter.loadMoreEnd();//没有更多数据了
            return;
        }


        if (mPage == 1) {
            mBeanList11.clear();
            mBeanList11.addAll(beanList);
        }

        mAdapter.addData(mBeanList11);// 加载更多数据
        RefreshTypeMore();
    }

    private void initAdapter() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        mRecycleview.setLayoutManager(layoutManager);

        mAdapter = new GanKIoAdapter();
        mRecycleview.setAdapter(mAdapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecycleview.addItemDecoration(decoration);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GanIoTypeBean.ResultsBean item = (GanIoTypeBean.ResultsBean) adapter.getItem(position);
                if (item.getType().equals("福利")) {  //区分 Android  iOS 福利   (Android  iOS 没有图片链接)
                    AgentWebActivity.getCallingIntent(GankIoActivity.this, item.getUrl());
                } else {
                    if (position > UriUtil.imagUrl.length - 1) {
                        AgentWebActivity.getCallingIntent(GankIoActivity.this, UriUtil.imagUrl[16]);
                    } else {
                        AgentWebActivity.getCallingIntent(GankIoActivity.this, UriUtil.imagUrl[position]);
                    }
                }
            }
        });
    }

    /**
     * 刷新状态
     */
    private void RefreshTypeMore() {
        mSwipeRefresh.setRefreshing(false); //请求成功 关闭刷新;
        mAdapter.loadMoreComplete();
    }

    @Override
    public void responseError(Throwable e) {
        if (mPage == 1) {
            if (mBeanList11.size() == 0) {
                showError();
            }
        }else{
            mAdapter.loadMoreFail();  //显示错误的
        }
    }

    /**
     * mRecyclerView的滑动监听
     */
    private void AdapterScrollListener() {

        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        mAdapter.setOnLoadMoreListener(this, mRecycleview);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
       // mAdapter.setPreLoadNumber(1);
       // mAdapter.disableLoadMoreIfNotFullPage();//检查是否满一屏，如果不满足关闭loadMore
    }

    @Override
    protected void onDestroy() {
        mAdapter = null;
        mBeanList11.clear();
        super.onDestroy();
    }

    /**
     * 上拉加载
     * // 滑动最后一个Item的时候回调onLoadMoreRequested方法
     * mAdapter.setOnLoadMoreListener(this,mRecycleview );
     */
    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mGankIoPresenter.InitNetWork(mStringExtra, number, mPage); //下拉刷新的时候 请求网络
    }
}
