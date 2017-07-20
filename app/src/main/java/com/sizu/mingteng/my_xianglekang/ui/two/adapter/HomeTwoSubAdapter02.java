package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.global.UriUtil;
import com.sizu.mingteng.my_xianglekang.ui.two.activity.AgentWebActivity;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;
import com.sizu.mingteng.my_xianglekang.util.StringUtils;
import com.sizu.mingteng.my_xianglekang.util.glide.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/19.
 */

public class HomeTwoSubAdapter02 extends DelegateAdapter.Adapter<BaseViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;

    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private List<HomeTwoBean.ResultsBean> mList;

    public HomeTwoSubAdapter02(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public HomeTwoSubAdapter02(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_subadapter, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        //TODO 对象创建的时候就已经周这个方法了 但是 集合这个时候还是0没有数据
        if (mList.size()!=0){
            ImageView mItem_image = (ImageView) holder.getItemView(R.id.item_image);
            ImageUtils.setImage(mContext, UriUtil.imagUrl[position+2], R.drawable.img_two_bi_one, mItem_image);
            holder.setItemView(R.id.item_time, mList.get(position).getPublishedAt())
                    .setItemView(R.id.item_title, mList.get(position).getType())
                    .setText(R.id.item_cade, StringUtils.decode(mList.get(position).getDesc()));
            holder.getViewHolder().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AgentWebActivity.getCallingIntent(mContext,mList.get(position).getUrl());
                }
            });
        }

    }


    @Override
    protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public HomeTwoSubAdapter02 setData(List<HomeTwoBean.ResultsBean> data) {
        mList = data;
        notifyDataSetChanged();
        return this;
    }
}
