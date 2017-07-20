package com.sizu.mingteng.my_xianglekang.util;

import android.app.Activity;

import com.sizu.mingteng.my_xianglekang.App;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/5.
 * android退出程序的工具类，使用单例模式
 * 1.在Activity的onCreate()的方法中调用addActivity()方法添加到mActivityList
 * 2.你可以在Activity的onDestroy()的方法中调用delActivity()来删除已经销毁的Activity实例
 * 这样避免了mActivityList容器中有多余的实例而影响程序退出速度
 *
 */

public class ExitAppUtils {
    /**
     * 转载Activity的容器
     */
    private List<Activity> mActivityList = new LinkedList<Activity>();
    private static ExitAppUtils instance = new ExitAppUtils();

    /**
     * 将构造函数私有化
     */
    private ExitAppUtils() {
    }

    public List<Activity> getActivityList() {
        return mActivityList;
    }

    /**
     * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
     *
     * @return
     */
    public static ExitAppUtils getInstance() {
        return instance;
    }


    /**
     * 添加Activity实例到mActivityList中，在onCreate()中调用
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
      MyLogger.e("aaa  addActivity", activity.getClass().getSimpleName());
        mActivityList.add(activity);
    }

    /**
     * 从容器中删除多余的Activity实例，在onDestroy()中调用
     *
     * @param activity
     */
    public void delActivity(Activity activity) {
        MyLogger.e("aaa  delActivity", activity.getClass().getSimpleName());
        mActivityList.remove(activity);
    }


    /**
     * 退出程序的方法
     */
    public void exit() {
        if (App.getContext() != null) {
           // MobclickAgent.onKillProcess(App.getContext()); //友盟
        }
        for (Activity activity : mActivityList) {
            MyLogger.e("aaa", activity.getClass().getSimpleName());
            activity.finish();
        }

        System.exit(0);
    }

    public void exitToHome() {
        for (Activity activity : mActivityList) {
            MyLogger.e("aaa", activity.getClass().getSimpleName());
            if (!"MainActivity".equals(activity.getClass().getSimpleName())) {
                activity.finish();
            }
        }

    }

}
