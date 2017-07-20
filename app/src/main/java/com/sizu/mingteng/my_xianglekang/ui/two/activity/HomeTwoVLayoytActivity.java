package com.sizu.mingteng.my_xianglekang.ui.two.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.databinding.ActiviyTwoVlayoutBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/14.
 *
 * vlayout 的使用
 */

public class HomeTwoVLayoytActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private  ActiviyTwoVlayoutBinding  mHomeTwoBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mHomeTwoBinding= DataBindingUtil.setContentView(this, R.layout.activiy_two_vlayout);
        initView();

    }

    public void initView() {
        mRecyclerView =mHomeTwoBinding.mRecyclerView;
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool(); //回收视图
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        //设置Adapter列表
        List<DelegateAdapter.Adapter> adapterList = new LinkedList<>();
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置Item个数
        linearLayoutHelper.setItemCount(1);
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(1);
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.setMarginBottom(100);

        adapterList.add(new SubAdapter(this,linearLayoutHelper, 1) {

            @Override
            public void onViewRecycled(MainViewHolder holder) {
                if (holder.itemView instanceof ViewPager) {
                    ((ViewPager) holder.itemView).setAdapter(null);
                }
            }

            @Override
            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1)
                    return new MainViewHolder(
                            LayoutInflater.from(HomeTwoVLayoytActivity.this).inflate(R.layout.view_pager , parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                if (holder.itemView instanceof ViewPager) {
                    /*ViewPager viewPager = (ViewPager) holder.itemView;
                    viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
                    // from position to get adapter
                    viewPager.setAdapter(new PagerAdapter(this, viewPool));*/
                }
            }
        });

        GridLayoutHelper layoutHelper;  //2
        layoutHelper = new GridLayoutHelper(4);
        layoutHelper.setMargin(0, 10, 0, 10);
        layoutHelper.setHGap(3);
        layoutHelper.setAspectRatio(4f);
        adapterList.add(new SubAdapter(this, layoutHelper, 8));



        GridLayoutHelper layoutHelper02;//4
        layoutHelper02 = new GridLayoutHelper(2);
        layoutHelper02.setMargin(0, 10, 0, 10);
        layoutHelper02.setHGap(3);
        layoutHelper02.setAspectRatio(3f);
        adapterList.add(new SubAdapter(this, layoutHelper02, 2));

        //一托N
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
        helper.setBgColor(0xff876384);
        helper.setMargin(10, 10, 10, 10);
        helper.setPadding(10, 10, 10, 10);
        adapterList.add(new SubAdapter(this, helper, 3) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
//                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                    layoutParams.leftMargin = 10;
//                    layoutParams.topMargin = 10;
//                    layoutParams.rightMargin = 10;
//                    layoutParams.bottomMargin = 10;
//                    holder.itemView.setLayoutParams(layoutParams);
            }
        });

        OnePlusNLayoutHelper helper01 = new OnePlusNLayoutHelper();
        helper01.setBgColor(0xff876384);
        helper01.setMargin(0, 10, 0, 10);
        adapterList.add(new SubAdapter(this, helper01, 4));


        OnePlusNLayoutHelper helper02 = new OnePlusNLayoutHelper();
        helper02.setBgColor(0xff876384);
        helper02.setMargin(0, 10, 0, 10);
        adapterList.add(new SubAdapter(this, helper02, 5));


        OnePlusNLayoutHelperEx helper03 = new OnePlusNLayoutHelperEx();
        helper03.setBgColor(0xff876384);
        helper03.setMargin(0, 10, 0, 10);
        adapterList.add(new SubAdapter(this, helper03, 5));

        OnePlusNLayoutHelperEx helper04 = new OnePlusNLayoutHelperEx();
        helper04.setBgColor(0xff876384);
        helper04.setMargin(0, 10, 0, 10);
        helper04.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f});
        adapterList.add(new SubAdapter(this, helper04, 5));

        OnePlusNLayoutHelperEx helper05 = new OnePlusNLayoutHelperEx();
        helper05.setBgColor(0xff876384);
        helper05.setMargin(0, 10, 0, 10);
        helper05.setColWeights(new float[]{20f, 80f, 0f, 60f, 20f});
        helper05.setAspectRatio(4);
        adapterList.add(new SubAdapter(this, helper05, 5));

        OnePlusNLayoutHelperEx helper06 = new OnePlusNLayoutHelperEx();
        helper06.setBgColor(0xff876384);
        helper06.setMargin(0, 10, 0, 10);
        adapterList.add(new SubAdapter(this, helper06, 6));

        OnePlusNLayoutHelperEx helper07 = new OnePlusNLayoutHelperEx();
        helper07.setBgColor(0xff876384);
        helper07.setMargin(0, 10, 0, 10);
        adapterList.add(new SubAdapter(this, helper07, 7));

        OnePlusNLayoutHelperEx helper08 = new OnePlusNLayoutHelperEx();
        helper08.setBgColor(0xff876384);
        helper08.setMargin(0, 10, 0, 10);
        helper08.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f, 30f, 30f});
        adapterList.add(new SubAdapter(this, helper08, 7));

        OnePlusNLayoutHelperEx helper09 = new OnePlusNLayoutHelperEx();
        helper09.setBgColor(0xffed7612);
//            helper.setMargin(10, 10, 10, 10);
//            helper.setPadding(10, 10, 10, 10);
        helper09.setColWeights(new float[]{30f, 20f, 50f, 40f, 30f, 35f, 35f});
        adapterList.add(new SubAdapter(this, helper09, 7));

        //67
        StickyLayoutHelper layoutHelper10 = new StickyLayoutHelper();
        layoutHelper10.setAspectRatio(4);
        //会将视图固定在顶部（stickyStart = true）或者底部（stickyStart = false），固定的位置支持设置偏移量offset。
        layoutHelper10.setStickyStart(true);
        adapterList.add(new SubAdapter(this, layoutHelper10, 1
                , new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50)));


        //滑动到当前位置才 悬浮显示  68
        ScrollFixLayoutHelper layoutHelper11 = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 100, 100);
        layoutHelper11.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
        adapterList.add(new SubAdapter(this, layoutHelper11, 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(100, 100);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });


        adapterList.add(new SubAdapter(this, new LinearLayoutHelper(), 50));

        //一直悬浮
        FloatLayoutHelper layoutHelper12 = new FloatLayoutHelper();
        layoutHelper12.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper12.setDefaultLocation(100, 600);
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(150, 150);
        adapterList.add(new SubAdapter(this, layoutHelper12, 1, layoutParams));


        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        delegateAdapter.setAdapters(adapterList);
        mRecyclerView.setAdapter(delegateAdapter);
    }
    // RecyclableViewPager
  /*  static class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {
        public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }
        @Override
        public int getCount() {
            return 6;
        }
        @Override
        public void onBindViewHolder(MainViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
        }
        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }
*/

    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
