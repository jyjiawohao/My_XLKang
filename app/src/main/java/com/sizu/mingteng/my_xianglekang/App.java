package com.sizu.mingteng.my_xianglekang;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.sizu.mingteng.my_xianglekang.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/24.
 */

public class App extends Application{
    public static Context context;
    public static Handler mhandler;
    private List<BaseActivity> mBaseActivityList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mhandler=new Handler();
    }
    public static Context getContext() {
        return context;
    }
    /**
     * 存储 activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        if (!mBaseActivityList.contains(activity)) { //如果集合中不存在就添加进集合
            mBaseActivityList.add(activity);
        }
    }
    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        mBaseActivityList.remove(activity);
    }

}
