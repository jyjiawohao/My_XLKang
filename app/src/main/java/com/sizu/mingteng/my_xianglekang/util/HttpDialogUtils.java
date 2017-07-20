package com.sizu.mingteng.my_xianglekang.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2017/7/14.
 */

public class HttpDialogUtils {

    private static ProgressDialog sProgressDialog;
    private static WeakReference<Context> mThreadActivityRef;//弱引用
    /**
     * 显示网络请求的dialog
     *
     * @param context
     * @param canceledOnTouchOutside
     * @param messageText
     */
    public static void showDialog(Context context, boolean canceledOnTouchOutside, String messageText) {
        if (context != null) {
            if (TextUtils.isEmpty(messageText)) {
                showWaitDialog(context, canceledOnTouchOutside);
            } else {
                showWaitDialog(context, messageText, canceledOnTouchOutside);
            }
        }
    }

    private static void showWaitDialog(Context context, boolean canceledOnTouchOutside) {
        initProgressDialog(context);
        sProgressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        sProgressDialog.show();
    }


    private static void showWaitDialog(Context context, String messageText, boolean canceledOnTouchOutside) {
        initProgressDialog(context);
        sProgressDialog.setMessage(messageText);
        sProgressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        sProgressDialog.show();
    }

    public static void dismissDialog() {
        sProgressDialog.dismiss();
    }

    private static void initProgressDialog(Context context) {
        if (sProgressDialog == null){
            mThreadActivityRef = new WeakReference<>(context);
            sProgressDialog = new ProgressDialog(mThreadActivityRef.get());
            sProgressDialog.setCancelable(true); // 是否可以按“返回键”消失
        }
    }

}
