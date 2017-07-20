package com.sizu.mingteng.my_xianglekang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sizu.mingteng.my_xianglekang.global.GlobalConstant;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;
import com.sizu.mingteng.my_xianglekang.util.NoDoubleClickUtils;
import com.sizu.mingteng.my_xianglekang.widget.CircleTextProgressbar;

import java.lang.ref.WeakReference;

import static com.sizu.mingteng.my_xianglekang.global.GlobalConstant.INDEX_ONE;
import static com.sizu.mingteng.my_xianglekang.global.GlobalConstant.INDEX_TWo;

/**
 * Created by lenovo on 2017/6/2.
 */

public class SplashActivity extends AppCompatActivity implements Runnable, View.OnClickListener {

    private static final String TAG = "SplashActivity";
    private CircleTextProgressbar mTvSkip;
    private MyHandler mInstance;


    private MyHandler handler;

    //第一步，将Handler改成静态内部类。
    private static class MyHandler extends Handler {
        //第二步，将需要引用Activity的地方，改成弱引用。
        private WeakReference<SplashActivity> atyInstance;

        public MyHandler(SplashActivity aty) {
            this.atyInstance = new WeakReference<SplashActivity>(aty);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity aty = atyInstance == null ? null : atyInstance.get();
            //如果Activity被释放回收了，则不处理这些消息
            if (aty == null || aty.isFinishing()) {
                return;
            }
            startNextActivity(msg, aty);
        }
    }

    private static void startNextActivity(Message msg, SplashActivity aty) {
        Intent intent;
        switch (msg.what) {
            case INDEX_ONE:
                MyLogger.e(TAG, "1111");
                intent = new Intent(aty, MainActivity.class);
                aty.startActivity(intent);
                aty.overridePendingTransition(R.anim.zoom_out_entry, R.anim.hold);
                aty.finish();
                break;
            case INDEX_TWo:
                MyLogger.e(TAG, "2222");
                intent = new Intent(aty, MainActivity.class);
                aty.startActivity(intent);
                aty.overridePendingTransition(R.anim.zoom_out_entry, R.anim.hold);
                aty.finish();
                break;
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //沉浸式导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //  window.setNavigationBarColor(Color.TRANSPARENT);
        }


        mTvSkip = (CircleTextProgressbar) findViewById(R.id.tv_red_skip);
        LinearLayout llRedSkip = (LinearLayout) findViewById(R.id.ll_red_skip);
        llRedSkip.setOnClickListener(this);



        mTvSkip.setOutLineColor(Color.TRANSPARENT);
        mTvSkip.setInCircleColor(Color.parseColor("#EEEEEE"));
        mTvSkip.setProgressColor(Color.RED);
        mTvSkip.setProgressLineWidth(3);
        mTvSkip.setTimeMillis(3000);
        mTvSkip.setProgressType(CircleTextProgressbar.ProgressType.COUNT);
       /* mTvSkip.setCountdownProgressListener(0, new CircleTextProgressbar.OnCountdownProgressListener() {
            @Override
            public void onProgress(int what, int progress) {
                startNextActivity();
                finish();
            }
        });*/
        mTvSkip.start();
        //第一个参数表示 总的时间为30000毫秒,间隔1000毫秒
     /* new CountDownTimer(60000,1000) {
          @Override
          public void onTick(long millisUntilFinished) {
          }
          @Override
          public void onFinish() {
          }
      };*/
        handler = new MyHandler(this);
        /**
         * 延迟2秒钟跳转
         */
        new Thread(this).start();
    }

    public void onClick(View v) {
        if (NoDoubleClickUtils.isDoubleClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.ll_red_skip:
                Message obtain = Message.obtain();
                obtain.what = INDEX_TWo;
                handler.sendMessage(obtain);
                break;
        }
    }


    @Override
    public void run() {
        SystemClock.sleep(3000);

        Message obtains = Message.obtain();
        obtains.what = GlobalConstant.INDEX_ONE;
        handler.sendMessage(obtains);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


}
