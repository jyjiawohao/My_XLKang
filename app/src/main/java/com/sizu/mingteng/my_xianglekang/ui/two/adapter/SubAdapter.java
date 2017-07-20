package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.ui.two.activity.AgentWebActivity;
import com.sizu.mingteng.my_xianglekang.ui.two.bean.HomeTwoBean;

/**
 * Created by lenovo on 2017/7/18.
 */

public class SubAdapter extends DelegateAdapter.Adapter<BaseViewHolder> implements View.OnClickListener {

    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private HomeTwoBean.ResultsBean mResultsBean1;


    public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
        mResultsBean1 = new HomeTwoBean.ResultsBean();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_type_title, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.getView(R.id.item_tv_android).setOnClickListener(this);
        holder.getView(R.id.item_tv_ios).setOnClickListener(this);
        holder.getView(R.id.item_tv_welfare).setOnClickListener(this);
    }


    @Override
    protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public void setData(HomeTwoBean.ResultsBean resultsBean) {
        mResultsBean1 = resultsBean;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_tv_android:
                AgentWebActivity.getCallingIntent(mContext,"android");
                break;
            case R.id.item_tv_ios:
                AgentWebActivity.getCallingIntent(mContext,"ios");
                break;
            case R.id.item_tv_welfare:
                AgentWebActivity.getCallingIntent(mContext,"福利");
                break;
            default:
                break;
        }

    }
}

