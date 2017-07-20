package com.sizu.mingteng.my_xianglekang.util;

/**
 * Created by lenovo on 2017/6/15.
 * <p>
 * 没有双击 点击
 * <p>
 *     点击事件处理之前调用
 * public void onClick(View view) {
 *      if (NoDoubleClickUtils.isDoubleClick()) {
 *          return;
 *          }
 *      switch (view.getId())
 */

public class NoDoubleClickUtils {
    private static long lastClickTime;
    private final static int SPACE_TIME = 500;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime >
                SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        MyLogger.e("aaa", isClick2 + "");
        return isClick2;
    }

}
