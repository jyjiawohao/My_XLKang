package com.sizu.mingteng.my_xianglekang.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2017/7/17.
 *
 *
 */

//TODO 有BUG 没有解决 占时不推荐使用
//第一步，将Handler改成静态内部类。
public class MyHandler extends Handler {
    //第二步，将需要引用Activity的地方，改成弱引用。
    private WeakReference<Activity> atyInstance;

    public MyHandler(Activity aty, handleListener mHandleListener) {
        this.atyInstance = new WeakReference<Activity>(aty);
        this.mHandleListener= mHandleListener;
    }

    private  static MyHandler sHandler;
    public  static MyHandler getInstance(Activity context,handleListener mHandleListener){
        if (sHandler==null){
            synchronized (MyHandler.class){
                if (sHandler==null){
                    //将传入的context转换成Application的context
                    sHandler = new MyHandler(context,mHandleListener);
                }
            }
        }
        return  sHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Activity aty = atyInstance == null ? null : atyInstance.get();
        //如果Activity被释放回收了，则不处理这些消息
        if (aty == null || aty.isFinishing()) {
            return;
        }
        if (mHandleListener!=null)
            mHandleListener.MessageListener(msg,aty);
    }



    /**
     * 发送消息
     */
    public void OnsendMessage(Message message){
        this.sendMessage(message);
    }
    /**
     * 发送消息
     */
    public void sendEmptyMessage(Message message){
        this.sendEmptyMessage(message);
    }


    /**
     * 在Activity退出的时候移除回调
     */
    public void removeCallback(){
        this.removeCallbacksAndMessages(null);
    }

    private handleListener mHandleListener;
    public  interface handleListener{
        void MessageListener(Message msg, Activity aty);
    }
}
