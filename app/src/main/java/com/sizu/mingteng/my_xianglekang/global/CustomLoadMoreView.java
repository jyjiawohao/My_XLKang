package com.sizu.mingteng.my_xianglekang.global;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.sizu.mingteng.my_xianglekang.R;

/**
 * Created by lenovo on 2017/6/8.
 */

public class CustomLoadMoreView extends LoadMoreView {
    @Override public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}