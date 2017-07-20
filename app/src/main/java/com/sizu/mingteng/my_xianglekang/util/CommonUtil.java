package com.sizu.mingteng.my_xianglekang.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.sizu.mingteng.my_xianglekang.App;


/**
 * 在没有上下文的方法里面获取上下文
 */
public class CommonUtil {
    /**
     * 在主线程执行一段任务
     * @param
     */
    public static void  runOnUIThread(Runnable r){
        App.mhandler.post(r);  //调用 Application 里面的handler

    }

    /**
     * 移除子View
     * @param child
     */

    public static void removeSelfFromParent(View child){
        if (child!=null){
            ViewParent parent = child.getParent();
            if(parent!=null && parent instanceof ViewGroup){
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);//移除子View
            }
        }
    }

    /**
     * 根据全局的上下文
     * 获取资源目录的图片
     *
     */
    public static Drawable getDrawable(int id){
        return App.context.getResources().getDrawable(id);
    }

    /**
     * 字符串
     */
    public static String getString(int id){
        return App.context.getResources().getString(id);
    }

    /**
     * 数组
     * @param id
     * @return
     */
    public static String[] getStringArray(int id){
        return App.context.getResources().getStringArray(id);
    }

    /**
     * 颜色
     */
    public static int getColor(int id){
        return App.context.getResources().getColor(id);
    }
    /**
     * 获取dp资源，并且会自动将dp值转为px值
     * @param id
     * @return
     */
    public static int getDimens(int id){
        return App.context.getResources().getDimensionPixelSize(id);
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
}
