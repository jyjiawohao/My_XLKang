package com.sizu.mingteng.my_xianglekang.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sizu.mingteng.my_xianglekang.App;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.databinding.FragmentHomeOneBinding;
import com.sizu.mingteng.my_xianglekang.databinding.LayoutHomeOneHeaderBinding;
import com.sizu.mingteng.my_xianglekang.global.GlideImageLoader;
import com.sizu.mingteng.my_xianglekang.ui.activity.MovieDetailActivity;
import com.sizu.mingteng.my_xianglekang.ui.fragment.adapter.HomeOneAdapter;
import com.sizu.mingteng.my_xianglekang.ui.fragment.impl.HomeOneViewPresenterImpl;
import com.sizu.mingteng.my_xianglekang.ui.fragment.persenter.HomeOneViewPresenter;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/24.
 * 首页
 */

public class HomeOneFragment extends Fragment implements HomeOneView, SwipeRefreshLayout.OnRefreshListener {

    private HomeOneViewPresenter mHomeOneViewPresenter;
    private FragmentHomeOneBinding mHomeOneBinding;
    private HomeOneAdapter mHomeOneAdapter;
    private LayoutHomeOneHeaderBinding mHeaderBinding;
    private Banner mBanner;
    private final String TAG = "HomeOneFragment";
    private ArrayList<MovieBean.SubjectsBean> mArrayList;
    private View mNotDataView;
    private View mErrorView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_home_one, container, false);
        mHomeOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_one, container, false);
        return mHomeOneBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mArrayList = new ArrayList<>();
        mSwipeRefreshLayout = mHomeOneBinding.srlRefreshLayout;
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

       // mHomeOneBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mHomeOneBinding.mRecyclerView.setLayoutManager(manager);
        mHomeOneBinding.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
      /*
        mHomeOneAdapter = new HomeOneAdapter(getContext(), mArrayList);
        mHomeOneAdapter.addHeaderView(bindingHeader());
        mHomeOneAdapter.setHeaderViewAsFlow(false);//设置头部、尾部不占一行
        mHomeOneBinding.mRecyclerView.setAdapter(mHomeOneAdapter);*/

       // mHomeOneAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mHomeOneBinding.mRecyclerView.getParent());


        //暂无数据
        mNotDataView = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, (ViewGroup) mHomeOneBinding.mRecyclerView.getParent(), false);
        mNotDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeOneViewPresenter.bannerInitNetWork();
            }
        });
        //网络错误
        mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.pager_error, (ViewGroup) mHomeOneBinding.mRecyclerView.getParent(), false);
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeOneViewPresenter.bannerInitNetWork();
            }
        });
    }

    private void initData() {
        mHomeOneViewPresenter = new HomeOneViewPresenterImpl(this);
       // mHomeOneViewPresenter.bannerInitNetWork();

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mHomeOneViewPresenter.bannerInitNetWork();
            }
        });
    }

    @Override
    public void onRefresh() {
        mHomeOneViewPresenter.bannerInitNetWork();
    }

    @Override
    public void onSuccess(MovieBean movieBean) {
        List<MovieBean.SubjectsBean> subjects = movieBean.getSubjects();
        if (mHomeOneAdapter == null) {
            mHomeOneAdapter = new HomeOneAdapter(getActivity(), subjects);
            mHomeOneBinding.mRecyclerView.setAdapter(mHomeOneAdapter);
            mHomeOneAdapter.addHeaderView(bindingHeader());
            mHomeOneAdapter.setHeaderViewAsFlow(false);//设置头部、尾部不占一行
            setItemAdapterClick();
        } else {
            mHomeOneAdapter.notifyDataSetChanged();
        }
        if (subjects==null&&subjects.size()==0){
            mHomeOneAdapter.setEmptyView(mNotDataView);
        }
      /*  mArrayList.clear();
        mArrayList.addAll(subjects);
        //空视图
        if (mArrayList.size() == 0) {
            mHomeOneAdapter.setEmptyView(mNotDataView);
        }
        mHomeOneAdapter.setNewData(mArrayList);*/
        if (subjects != null)
            initBanner(subjects);
    }


    /**
     * 适配器点击事件
     */
    private void setItemAdapterClick() {
        mHomeOneBinding.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MovieBean.SubjectsBean item= (MovieBean.SubjectsBean) adapter.getItem(position);

               /* Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("bean", item);
                EasyTransitionOptions options =
                        EasyTransitionOptions.makeTransitionOptions(
                                getActivity(),
                                view.findViewById(R.id.iv_one_photo));
                EasyTransition.startActivity(intent, options);*/
                MovieDetailActivity.start(getActivity(),item,view.findViewById(R.id.iv_one_photo));
            }
        });
    }

    @Override
    public void onError(Throwable e) {
        //mHomeOneAdapter.setEmptyView(mErrorView);
    }

    @Override
    public void onComplete() {
        mSwipeRefreshLayout.setRefreshing(false); //关闭刷新
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeOneAdapter = null;
    }

    private View bindingHeader() {
        // View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_one_header, (ViewGroup) mHomeOneBinding.mRecyclerView.getParent(), false);
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(App.getContext()), R.layout.layout_home_one_header, (ViewGroup) mHomeOneBinding.mRecyclerView.getParent(), false);
        mBanner = mHeaderBinding.banner;
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        return mHeaderBinding.getRoot();
    }

    private ArrayList<String> imagList;
    private ArrayList<String> titleList;

    private void initBanner(List<MovieBean.SubjectsBean> subjects) {
        if (imagList == null)
            imagList = new ArrayList<>();

        if (titleList == null)
            titleList = new ArrayList<>();

        titleList.clear();
        imagList.clear();

        for (int i = 0; i < 6; i++) {
            imagList.add(subjects.get(i).getImages().getLarge());
            titleList.add(subjects.get(i).getTitle());
        }

      /*   普通用法
      //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imagList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();*/

        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titleList);
        //设置图片集合
        mBanner.setImages(imagList);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) //开始轮播
            mBanner.startAutoPlay();
        MyLogger.e(TAG, "开启");
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.e(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        MyLogger.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null)//结束轮播
            mBanner.stopAutoPlay();
        MyLogger.e(TAG, "暂停");
    }


}
