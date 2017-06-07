package com.sizu.mingteng.my_xianglekang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lenovo on 2017/6/2.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
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
        /**
         * 延迟2秒钟跳转
         */
        new Handler().postDelayed(new Runnable(){

            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                /*Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
                finish();*/
            }

        }, 2000);

      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //将原先的跳转改成如下方式
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
                        }
                        finish();
                    }
                });
            }
        }).start();*/

    }
}
