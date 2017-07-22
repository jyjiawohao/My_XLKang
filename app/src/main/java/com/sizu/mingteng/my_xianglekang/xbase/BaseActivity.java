package com.sizu.mingteng.my_xianglekang.xbase;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.util.CommonUtil;
import com.sizu.mingteng.my_xianglekang.util.statusbar.StatusBarUtil;


/**
 * Created by lenovo on 2017/7/20.
 */

public abstract class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {
    // 布局view
    protected SV bindingView;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private TextView mTv_title;
    // 四类界面
    public View loading;  //网络加载中
    public View error;   //错误视图
    public View empty;  //没有数据
    private Button mError_btn_retry;
    private View mView;

    protected <T extends View> T getView(int id) {
        return (T) mView.findViewById(id);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //navigationBar();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        initView();

        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        //第一个参数为宽的设置，第二个参数为高的设置。
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        bindingView.getRoot().setLayoutParams(layoutParams);
        mFrameLayout.addView(bindingView.getRoot());
        getWindow().setContentView(mView);

        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtil.getColor(R.color.red));
        showProgress();
    }

    /**
     * 沉浸式导航栏  https://github.com/laobie/StatusBarUtil  最好使用这个框架
     */
    private void navigationBar() {
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
    }

    private void initView() {

        mToolbar = getView(R.id.toolbar);
        mTv_title = getView(R.id.tv_title);
        mFrameLayout = getView(R.id.frameLayout);
        loading = getView(R.id.loading);
        error = getView(R.id.error);
        empty = getView(R.id.empty);
        mError_btn_retry = getView(R.id.error_btn_retry);

       /* mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        mTv_title = (TextView) mView.findViewById(R.id.tv_title);
        mFrameLayout = (FrameLayout) mView.findViewById(R.id.frameLayout);
        loading = mView.findViewById(R.id.loading);
        error = mView.findViewById(R.id.error);
        empty = mView.findViewById(R.id.empty);
        mError_btn_retry = (Button) mView.findViewById(R.id.error_btn_retry);*/
        setToolBar();
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * 设置 标题
     *
     * @param text
     */
    public void setTitle(CharSequence text) {
        mTv_title.setText(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 设置 网络成功后加载的界面
     *
     * @return
     */
    //protected abstract int getLayoutId();

    /**
     * 设置耗时的操作
     */
    protected abstract void loadData();


    /**
     * 获取view
     *
     * @return
     */
    //protected abstract void getViewLayout(View mInflate);
    @Override
    protected void onRestart() {
        super.onRestart();
    }


    /**
     * 数据加载中  在加载中的时候 隐藏 除加载中的 所有布局
     */
    public void showProgress() {
        if (loading.getVisibility() != View.VISIBLE) {
            loading.setVisibility(View.VISIBLE);
        }

        if (error.getVisibility() != View.GONE) {
            error.setVisibility(View.GONE);
        }

        if (empty.getVisibility() != View.GONE) {
            empty.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }


    /**
     * 加载完成的状态  隐藏 除了完成视图布局 的所有布局
     */
    protected void showContentView() {
        if (loading.getVisibility() != View.GONE) {
            loading.setVisibility(View.GONE);
        }
        if (error.getVisibility() != View.GONE) {
            error.setVisibility(View.GONE);
        }
        if (empty.getVisibility() != View.GONE) {
            empty.setVisibility(View.GONE);
        }

        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (loading.getVisibility() != View.GONE) {
            loading.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }

        if (empty.getVisibility() != View.GONE) {
            empty.setVisibility(View.GONE);
        }
        if (error.getVisibility() != View.VISIBLE) {
            error.setVisibility(View.VISIBLE);
        }

        mError_btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                loadData();
            }
        });
    }

    /**
     * 暂无数据 视图
     */
    protected void showEmpty() {
        if (loading.getVisibility() != View.GONE) {
            loading.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (error.getVisibility() != View.GONE) {
            error.setVisibility(View.GONE);
        }
        if (empty.getVisibility() != View.VISIBLE) {
            empty.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

}
