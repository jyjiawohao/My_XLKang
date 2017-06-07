package com.sizu.mingteng.my_xianglekang.base;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sizu.mingteng.my_xianglekang.App;
import com.sizu.mingteng.my_xianglekang.util.ToastUtils;


/**
 * Created by lenovo on 2017/5/11.
 */

public class BaseActivity extends PermissionActivity {
    private App mApp;//需要在清单文件配置 getApplication
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *  所有的Activity都依附于一个Application，在Activity中只要通过 getApplication（）方法，
         *  就能拿到当前应用中的Application对象
         */
        mApp = (App)getApplication();
        mApp.addActivity(this);
        mProgressDialog = new ProgressDialog(this); //设置进度对话框
        mProgressDialog.setCancelable(false);
    }

    /**
     * 显示diaog
     * @param msg
     */
    public void showDialog(String msg){
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 横向的 进度条 对话框
     */
    public void showMaterialDialog(){
       /* new MaterialDialog.Builder(this)
                .title("数据下载中")
                .content("请等待")
                .progress(true, 100)
                .progressIndeterminateStyle(true)
                .show();*/

       /* boolean showMinMax = true;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("数据下载中")
                .content("请等待")
                .progress(false, 150, showMinMax)
                .show();*/
        /*
        * new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show();*/
    }

    /**
     * 隐藏 diaog
     */
    public void hideDialog(){
        mProgressDialog.hide();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApp.removeActivity(this);
        mProgressDialog.dismiss();
    }

    /**
     * 吐司
     * @param msg
     */
    public void showToast(String msg){
        ToastUtils.showToast(this,msg);
    }

    /**
     * 带参数  是否需要 finish 当前activity
     * @param clazz
     * @param isFinish
     * @param contact
     */
    public void startActivity(Class clazz, boolean isFinish,String username, String contact) {
        Intent intent = new Intent(this,clazz);
        if (contact!=null){
            intent.putExtra(username,contact);
        }
        startActivity(intent);
        if (isFinish){
            finish();
        }
    }
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mApp, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mApp, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }



}
