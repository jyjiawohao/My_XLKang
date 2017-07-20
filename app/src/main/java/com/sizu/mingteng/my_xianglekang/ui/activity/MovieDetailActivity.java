package com.sizu.mingteng.my_xianglekang.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.bean.MovieDetailBean;
import com.sizu.mingteng.my_xianglekang.databinding.ActivityMovieDatailBinding;
import com.sizu.mingteng.my_xianglekang.ui.fragment.MovieDetailPresenter;
import com.sizu.mingteng.my_xianglekang.ui.fragment.MovieDetailView;
import com.sizu.mingteng.my_xianglekang.ui.fragment.adapter.CastsAdapter;
import com.sizu.mingteng.my_xianglekang.ui.fragment.impl.MovieDetailPresenterImpl;
import com.sizu.mingteng.my_xianglekang.util.CommonUtil;
import com.sizu.mingteng.my_xianglekang.util.glide.ImageUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

/**
 * Created by lenovo on 2017/6/5.
 * 电影详情
 */

public class MovieDetailActivity extends AutoLayoutActivity implements MovieDetailView {
    private MovieBean.SubjectsBean mSubjectsBean;
    public final static String EXTRA_PARAM = "bookBean";
    private ActivityMovieDatailBinding mBinding;
    private MovieDetailPresenter mMovieDetailPresenter;
    private CastsAdapter mCastsAdapter;

    /**
     * @param activity     activity
     * @param positionData bean
     * @param imageView    imageView
     */
    public static void getCallingIntent(Activity activity, MovieBean.SubjectsBean positionData, View imageView) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(EXTRA_PARAM, positionData);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        imageView, CommonUtil.getString(R.string.transition_img));//与xml文件对应
        ActivityCompat.startActivity(activity, intent, options.toBundle());
        /* EasyTransitionOptions options=EasyTransitionOptions.makeTransitionOptions(activity,imageView);
        EasyTransition.startActivity(intent,options);*/
       /* if (android.os.Build.VERSION.SDK_INT > 20) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, imageView, CommonUtil.getString(R.string.transition_img)).toBundle());
        } else {
            activity.startActivity(intent);
        }*/
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_datail);
        initVIew();
        initToolbar();
        initData();
    }


    private void initVIew() {
        if (getIntent() != null) {
            mSubjectsBean = (MovieBean.SubjectsBean) getIntent().getSerializableExtra(EXTRA_PARAM);
        }
        ImageUtils.loadIntoUseFitWidth(this, mSubjectsBean.getImages().getLarge(), R.drawable.img_two_bi_one, mBinding.views.ivOnePhoto);
    }

    private void initData() {
        //RetrofitManager.init(this);

        mBinding.movieContainer.xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.movieContainer.xRecyclerView.setNestedScrollingEnabled(false);

        mCastsAdapter = new CastsAdapter(this);
        mCastsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//动画效果
        mBinding.movieContainer.xRecyclerView.setAdapter(mCastsAdapter);

        mMovieDetailPresenter = new MovieDetailPresenterImpl(this);
        mMovieDetailPresenter.initNetWork(mSubjectsBean.getId());


       /* mCastsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MovieDetailBean.CastsBean item = (MovieDetailBean.CastsBean) adapter.getItem(position);
                if (item != null && !TextUtils.isEmpty(item.getAlt())) {
                    WebViewActivity.loadUrl(MovieDetailActivity.this, item.getAlt(), item.getName());
                }
            }
        });*/
        mBinding.movieContainer.xRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MovieDetailBean.CastsBean item = (MovieDetailBean.CastsBean) adapter.getItem(position);
                if (item != null && !TextUtils.isEmpty(item.getAlt())) {
                    WebViewActivity.loadUrl(MovieDetailActivity.this, item.getAlt(), item.getName());
                }
            }
        });
    }

    @Override
    public void Refresh(MovieDetailBean movieDetailBean) {
        if (movieDetailBean != null)
            mBinding.setMovie(movieDetailBean);
        List<MovieDetailBean.CastsBean> casts = movieDetailBean.getCasts();
        if (casts != null)
            mCastsAdapter.setNewData(casts);
    }

    /**
     * 设置toolbar
     */
    private void initToolbar() {
        mBinding.toolbar.setTitle(mSubjectsBean.getTitle());
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置导航栏打开
    }

    /**
     * 右边 菜单  默认图标不显示
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.movie_datail,menu);
        return true;
    }
    /**
     * 右边 菜单 默认图标不显示
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuBuilder builder = (MenuBuilder) menu;
        builder.setOptionalIconsVisible(true);
        return true;
    }

    /**
     * 菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://导航栏 返回键  因为和侧拉菜单同步了 此方法没有调用
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                break;
            case R.id.menu_movie:
                WebViewActivity.loadUrl(MovieDetailActivity.this, mSubjectsBean.getAlt(), mSubjectsBean.getTitle());
                break;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
