package com.sizu.mingteng.my_xianglekang.ui.two.vp;

import com.sizu.mingteng.my_xianglekang.bean.GanIoTypeBean;

import java.util.List;

/**
 * Created by lenovo on 2017/7/20.
 */

public interface GankIoView {
    void responseSuccess(List<GanIoTypeBean.ResultsBean> beanList);

    void responseError(Throwable e);
}
