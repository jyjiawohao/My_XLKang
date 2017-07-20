package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.allen.library.SuperTextView;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.ui.two.activity.AgentWebActivity;
import com.sizu.mingteng.my_xianglekang.util.ToastUtils;

/**
 * Created by lenovo on 2017/7/20.
 */

public class HomeTwoTitleAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;

    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private String mS1;//内容


    public HomeTwoTitleAdapter(Context context, LayoutHelper layoutHelper, String s, int count) {
        //设置宽高比
        //this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mS1 = s;
    }

    public HomeTwoTitleAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_two_title, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        SuperTextView mItem_tv = holder.getView(R.id.item_tv);
        mItem_tv.setLeftString(mS1);
        mItem_tv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                ToastUtils.showToast("点击了"+mS1);
            }
        });

    }


    @Override
    protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}