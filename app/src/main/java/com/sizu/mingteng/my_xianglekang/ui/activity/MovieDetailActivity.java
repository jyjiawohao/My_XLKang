package com.sizu.mingteng.my_xianglekang.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.MovieBean;
import com.sizu.mingteng.my_xianglekang.databinding.ActivityMovieDatailBinding;
import com.sizu.mingteng.my_xianglekang.util.CommonUtil;
import com.sizu.mingteng.my_xianglekang.util.ImageUtils;
import com.sizu.mingteng.my_xianglekang.util.PaletteUtil;

/**
 * Created by lenovo on 2017/6/5.
 * 电影详情
 */

public class MovieDetailActivity extends AppCompatActivity {
    private MovieBean.SubjectsBean mSubjectsBean;
    public final static String EXTRA_PARAM = "bookBean";
    private ActivityMovieDatailBinding mBinding;

    /**
     * @param activity      activity
     * @param positionData bean
     * @param imageView    imageView
     */
    public static void start(Activity activity, MovieBean.SubjectsBean positionData, View imageView) {
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
            mBinding.setMovie(mSubjectsBean);
        }

    }

    private void initToolbar() {
        mBinding.toolbar.setTitle(mSubjectsBean.getTitle());
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置导航栏打开
    }
    /**
     * 菜单的点击事件
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
        }
        return true;
    }

    private void initData() {
       ImageUtils.loadIntoUseFitWidth(this,mSubjectsBean.getImages().getLarge(),R.drawable.img_two_bi_one,mBinding.views.ivOnePhoto);

        //设置 取色器
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_nav_bg_drawerlayout);
        PaletteUtil.getInstance().init(bitmap, new PaletteUtil.PatternCallBack() {
            @Override
            public void onCallBack(Drawable drawable, int titleColor) {
               mBinding.collapsingToolbar.setBackgroundColor(titleColor);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
