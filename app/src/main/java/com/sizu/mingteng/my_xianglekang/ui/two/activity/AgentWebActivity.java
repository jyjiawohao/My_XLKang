package com.sizu.mingteng.my_xianglekang.ui.two.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.webview.BaseWebActivity;

/**
 * Created by lenovo on 2017/7/20.
 */

public class AgentWebActivity extends BaseWebActivity {
    private static final String URL = "url"; //路径地址

    @Override
    public String getUrl() {
        return getIntent().getStringExtra(URL);
    }

    public static void getCallingIntent(Context context, String url) {
        Intent intent = new Intent(context, AgentWebActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade_entry, R.anim.hold);
    }

    //关闭  覆盖
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.fade_exit);
    }
}
