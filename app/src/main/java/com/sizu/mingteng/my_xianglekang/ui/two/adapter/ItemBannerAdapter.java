package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/20.
 */

public class ItemBannerAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private Context mContext;

    private LayoutHelper mLayoutHelper;

    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private List<HomeTwoBean.ResultsBean> mList;

    public ItemBannerAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ItemBannerAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
        mList = new ArrayList<>();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        //TODO 对象创建的时候就已经周这个方法了 但是 集合这个时候还是0没有数据
    }


    @Override
    protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public ItemBannerAdapter setData(List<HomeTwoBean.ResultsBean> data) {
        mList = data;
        notifyDataSetChanged();
        return this;
    }
}
