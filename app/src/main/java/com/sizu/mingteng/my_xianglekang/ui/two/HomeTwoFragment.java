package com.sizu.mingteng.my_xianglekang.ui.two;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.sizu.mingteng.my_xianglekang.App;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.databinding.FragmentHomeTwoBinding;
import com.sizu.mingteng.my_xianglekang.global.GlideImageLoader;
import com.sizu.mingteng.my_xianglekang.global.UriUtil;
import com.sizu.mingteng.my_xianglekang.ui.two.activity.AgentWebActivity;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.BaseViewHolder;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.HomeTwoSubAdapter;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.HomeTwoSubAdapter02;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.HomeTwoTitleAdapter;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.ItemBannerAdapter;
import com.sizu.mingteng.my_xianglekang.ui.two.adapter.SubAdapter;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;
import com.sizu.mingteng.my_xianglekang.util.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/24.
 */

public class HomeTwoFragment extends Fragment implements HomeTwoView {

    private FragmentHomeTwoBinding mHomeTwoBinding;
    private RecyclerView mRecyclerView;
    private HomeTwoPresenter mHomeTwoPresenter;
    private List<DelegateAdapter.Adapter> mAdapterList;
    private DelegateAdapter mDelegateAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_home_two, container, false);
        mHomeTwoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_two, container, false);
        return mHomeTwoBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapterList();
        mHomeTwoPresenter = new HomeTwoPresenterImpl(this);
        mHomeTwoPresenter.InitNetWork("all", 20, 1);
    }

    private void initAdapterList() {
        mRecyclerView = mHomeTwoBinding.mRecyclerView;
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(App.context);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool(); //回收视图
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        mDelegateAdapter = new DelegateAdapter(layoutManager);
        mRecyclerView.setAdapter(mDelegateAdapter);
        //设置Adapter列表
        mAdapterList = new LinkedList<>();

        mHomeTwoBinding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 假的刷新  真实开发者中 请调用刷新请求网络
                mHomeTwoBinding.swipeContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void Success(final List<HomeTwoBean.ResultsBean> beanList) {
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置Item个数
        linearLayoutHelper.setItemCount(1);
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(1);
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.setMarginBottom(40);
        mAdapterList.add(new ItemBannerAdapter(getContext(), linearLayoutHelper, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

                ArrayList<String> arrayList = null;
                ArrayList<String> titleList = null;
                arrayList = new ArrayList<String>();
                titleList = new ArrayList<String>();

                for (int i = 0; i < 18; i++) {
                    arrayList.add(UriUtil.imagUrl[i]);
                    titleList.add(StringUtils.decode(beanList.get(i).getDesc()));
                }

                if (beanList.size() != 0) {
                    Banner Item_banner = holder.getView(R.id.item_banner);
                    //设置图片加载器
                    Item_banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    Item_banner.setImages(arrayList);
                    //设置标题集合（当banner样式有显示title时）
                    Item_banner.setBannerTitles(titleList);
                    //设置banner样式
                    Item_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                    //设置指示器位置（当banner模式中有指示器时）
                    Item_banner.setIndicatorGravity(BannerConfig.CENTER);
                    Item_banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            AgentWebActivity.getCallingIntent(getContext(), beanList.get(position).getUrl());
                        }
                    }); //点击事件
                    //banner设置方法全部调用完毕时最后调用
                    Item_banner.start();
                }
            }
        });

        LinearLayoutHelper linearLayoutHelper01 = new LinearLayoutHelper();
        //设置Item个数
        linearLayoutHelper.setItemCount(1);
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(1);
        SubAdapter subAdapter = new SubAdapter(getContext(), linearLayoutHelper01, 1);
        /// subAdapter.setData(beanList.get(0));//设置适配器数据
        mAdapterList.add(subAdapter);


       // setTitleItem("网格布局",false);

        GridLayoutHelper layoutHelper;  //2
        layoutHelper = new GridLayoutHelper(4);

        /**
         * .setMargin(0, 10, 0, 0); 设置上下左右边距的
         * @param leftMargin left margin
         * @param topMargin top margin
         * @param rightMargin right margin
         * @param bottomMargin bottom margin
         */
        //layoutHelper.setMargin(0, 10, 0, 10);
        layoutHelper.setHGap(3);
        layoutHelper.setAspectRatio(4f);

        HomeTwoSubAdapter homeTwoSubAdapter = new HomeTwoSubAdapter(getContext(), beanList, layoutHelper, 4);
        mAdapterList.add(homeTwoSubAdapter);


        setTitleItem("网格布局",true);
        GridLayoutHelper layoutHelper02 = new GridLayoutHelper(2);
        //layoutHelper02.setMargin(0, 0, 0, 0);
        layoutHelper02.setHGap(3);
        layoutHelper02.setAspectRatio(3f);
        //layoutHelper02.setMarginBottom(40); //设置布局底部与下个布局的间隔

        HomeTwoSubAdapter02 homeTwoSubAdapter02 = new HomeTwoSubAdapter02(getContext(), layoutHelper02, 2);
        homeTwoSubAdapter02.setData(beanList);
        mAdapterList.add(homeTwoSubAdapter02);

        setTitleItem("一托二布局",true);
        /**
         *   一托N
         */
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
        // helper.setBgColor(0xff876384);
        //helper.setMargin(0, 10, 0, 10);
        //helper.setPadding(10, 10, 10, 10);

        HomeTwoSubAdapter02 homeTwoSubAdapter03 = new HomeTwoSubAdapter02(getContext(), helper, 3);
        homeTwoSubAdapter03.setData(beanList);
        mAdapterList.add(homeTwoSubAdapter03);

        setTitleItem("一托三布局",true);
        OnePlusNLayoutHelper helper0 = new OnePlusNLayoutHelper();
        //  helper0.setMargin(0, 10, 0, 10);
        HomeTwoSubAdapter02 homeTwoSubAdapter021 = new HomeTwoSubAdapter02(getContext(), helper0, 4);
        homeTwoSubAdapter021.setData(beanList);
        mAdapterList.add(homeTwoSubAdapter021);

        setTitleItem("一托四布局",true);
        OnePlusNLayoutHelper helper01 = new OnePlusNLayoutHelper();
        //  helper01.setMargin(0, 10, 0, 10);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper01, 5).setData(beanList));

        setTitleItem("一托四布局+",true);
        OnePlusNLayoutHelperEx helper03 = new OnePlusNLayoutHelperEx();
        //helper03.setBgColor(0xff876384);
        // helper03.setMargin(0, 10, 0, 10);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper03, 5).setData(beanList));


        setTitleItem("一托四权重布局",true);
        OnePlusNLayoutHelperEx helper04 = new OnePlusNLayoutHelperEx();
        //  helper04.setMargin(0, 10, 0, 10);
        // helper04.setColWeights(new float[]{40f, 40f, 15f, 45f, 20f});
        /**
         * new float[]{40f, 45f, 15f, 40f, 20f} 百分比
         *1:左边百分比  2+3 =100%
         * 1+ 4+5 =100%
         */
        helper04.setColWeights(new float[]{40f, 45f, 15f, 40f, 20f});
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper04, 5).setData(beanList));

        setTitleItem("一托四权重长宽比布局",true);
        OnePlusNLayoutHelperEx helper05 = new OnePlusNLayoutHelperEx();
        // helper05.setMargin(0, 10, 0, 10);
        helper05.setColWeights(new float[]{20f, 60f, 20f, 60f, 20f});
        helper05.setAspectRatio(4);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper05, 5).setData(beanList));

        setTitleItem("一托五布局",true);
        OnePlusNLayoutHelperEx helper06 = new OnePlusNLayoutHelperEx();
        // helper06.setMargin(0, 10, 0, 10);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper06, 6).setData(beanList));

        setTitleItem("一托六布局",true);
        OnePlusNLayoutHelperEx helper07 = new OnePlusNLayoutHelperEx();
        // helper07.setMargin(0, 10, 0, 10);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper07, 7).setData(beanList));

        setTitleItem("一托6权重布局",true);
        OnePlusNLayoutHelperEx helper08 = new OnePlusNLayoutHelperEx();
        // helper08.setMargin(0, 10, 0, 10);
        helper08.setColWeights(new float[]{40f, 45f, 15f, 40f, 20f, 30f, 30f});
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper08, 7).setData(beanList));


        setTitleItem("垂直权重布局",true);
        OnePlusNLayoutHelperEx helper09 = new OnePlusNLayoutHelperEx();
       // helper09.setBgColor(0xffed7612);
        // helper.setMargin(0, 10, 0, 10);
//            helper.setPadding(10, 10, 10, 10);
        helper09.setColWeights(new float[]{30f, 20f, 50f, 40f, 30f, 35f, 35f});
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper09, 7).setData(beanList));


        setTitleItem("吸附布局",true);
        /**
         * 吸附布局，可将item吸附在顶部 以及 底部，并进行偏移。
         */
        StickyLayoutHelper layoutHelper10 = new StickyLayoutHelper();
        layoutHelper10.setAspectRatio(4);
        //layoutHelper10.setMargin(0, 10, 0, 10);
        //会将视图固定在顶部（stickyStart = true）或者底部（stickyStart = false），固定的位置支持设置偏移量offset。
        layoutHelper10.setStickyStart(true);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), layoutHelper10, 1
                , new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50)).setData(beanList));


        setTitleItem("滑动到当前位置才悬浮显示",true);
        //滑动到当前位置才 悬浮显示  68
        ScrollFixLayoutHelper layoutHelper11 = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 100, 100);
        // layoutHelper11.setMargin(0, 10, 0, 10);
        layoutHelper11.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), helper09, 7).setData(beanList));


        /**
         * 一直悬浮
         */
        /* FloatLayoutHelper layoutHelper12 = new FloatLayoutHelper();
        layoutHelper12.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper12.setDefaultLocation(100, 600);
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(150, 150);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), layoutHelper12, 1, layoutParams).setData(beanList));*/


        setTitleItem("瀑布流",true);
        /**
         * 瀑布流
         */
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(3, 0);
        // staggeredGridLayoutHelper.setMargin(0, 10, 0, 10);
        mAdapterList.add(new HomeTwoSubAdapter02(getContext(), staggeredGridLayoutHelper, beanList.size() - 3).setData(beanList));

        mDelegateAdapter.setAdapters(mAdapterList);
    }

    private void setTitleItem(String s, boolean is) {
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置Item个数
        linearLayoutHelper.setItemCount(1);
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(1);
        if (is) //是否设置边距
            linearLayoutHelper.setMargin(0, 40, 0, 0); //设置边距
        else
            linearLayoutHelper.setPaddingTop(10); //设置边距

        //linearLayoutHelper.setMarginTop(30);
        //设置布局底部与下个布局的间隔
        //linearLayoutHelper.setMarginBottom(40);
        HomeTwoTitleAdapter subAdapter = new HomeTwoTitleAdapter(getContext(), linearLayoutHelper, s, 1);
        mAdapterList.add(subAdapter);
    }
}
