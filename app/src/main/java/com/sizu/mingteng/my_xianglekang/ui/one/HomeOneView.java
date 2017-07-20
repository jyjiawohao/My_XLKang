package com.sizu.mingteng.my_xianglekang.ui.one;

import com.sizu.mingteng.my_xianglekang.bean.MovieBean;

/**
 * Created by lenovo on 2017/5/27.
 */

public interface HomeOneView {
    void onSuccess(MovieBean movieBean);

    void onError(Throwable e);

    void onComplete();
}
