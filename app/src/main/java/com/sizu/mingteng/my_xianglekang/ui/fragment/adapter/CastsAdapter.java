package com.sizu.mingteng.my_xianglekang.ui.fragment.adapter;

import android.content.Context;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.base.BaseDataBindingAdapter;
import com.sizu.mingteng.my_xianglekang.bean.MovieDetailBean;
import com.sizu.mingteng.my_xianglekang.databinding.ItemMovieDetailPersonBinding;


/**
 * Created by lenovo on 2017/6/8.
 */

public class CastsAdapter extends BaseDataBindingAdapter<MovieDetailBean.CastsBean,ItemMovieDetailPersonBinding> {
    private  Context mContext;

    public CastsAdapter(Context context) {
        super(R.layout.item_movie_detail_person, null);
        mContext = context;
    }
    @Override
    protected void convert(ItemMovieDetailPersonBinding binding, MovieDetailBean.CastsBean item) {
        binding.setItemMovie(item);
    }
}
