package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.global.UriUtil;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;
import com.sizu.mingteng.my_xianglekang.util.StringUtils;
import com.sizu.mingteng.my_xianglekang.util.glide.ImageUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/7/18.
 */

public class HomeTwoSubAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;

    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private List<HomeTwoBean.ResultsBean> mList;


    public HomeTwoSubAdapter(Context context, List<HomeTwoBean.ResultsBean> beanList, LayoutHelper layoutHelper, int count) {
        //设置宽高比
        //this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mList = beanList;

    }

    public HomeTwoSubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_subadapter, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView mItem_image = (ImageView) holder.getItemView(R.id.item_image);
        ImageUtils.setImage(mContext, UriUtil.imagUrl[position], R.drawable.img_two_bi_one, mItem_image);
        holder.setItemView(R.id.item_time, mList.get(position + 1).getPublishedAt())
                .setItemView(R.id.item_title, mList.get(position + 1).getType())
                .setText(R.id.item_cade, StringUtils.decode(mList.get(position).getDesc()));
    }


    @Override
    protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}


