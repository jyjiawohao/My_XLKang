package com.sizu.mingteng.my_xianglekang.ui.fragment.adapter;

import android.app.Activity;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.base.BaseDataBindingAdapter;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.databinding.ItemHomeOneBinding;
import com.sizu.mingteng.my_xianglekang.ui.fragment.persenter.HomeOneAdapterPresenter;
import com.sizu.mingteng.my_xianglekang.util.ImageUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/6/1.
 */

public class HomeOneAdapter extends BaseDataBindingAdapter<MovieBean.SubjectsBean, ItemHomeOneBinding> {
    private HomeOneAdapterPresenter mPresenter;
    private Activity mActivity;

    public HomeOneAdapter(Activity context, List<MovieBean.SubjectsBean> subjects) {
        super(R.layout.item_home_one, subjects);
        mActivity = context;
        mPresenter = new HomeOneAdapterPresenter();
    }

    @Override
    protected void convert(final ItemHomeOneBinding binding, final MovieBean.SubjectsBean item) {
        binding.setMovie(item);
       // Glide.with(mContext).load(item.getImages().getLarge()).placeholder(R.drawable.img_two_bi_one).into(binding.photo);
        ImageUtils.loadIntoUseFitWidth(mContext,item.getImages().getLarge(),R.drawable.img_two_bi_one,binding.ivOnePhoto);
    }
}
