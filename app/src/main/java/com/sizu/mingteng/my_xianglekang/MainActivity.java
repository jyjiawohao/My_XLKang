package com.sizu.mingteng.my_xianglekang;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sizu.mingteng.my_xianglekang.base.BaseActivity;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;
import com.sizu.mingteng.my_xianglekang.util.ToastUtils;

import static com.sizu.mingteng.my_xianglekang.R.id.drawerLayout;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private int[] titleIds = {R.string.One, R.string.Two, R.string.Three, R.string.Four, R.string.Five};
    private BottomNavigationBar mBottomNavigationBar;
    private BadgeItem mBadgeItem;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private TextView mTv_title;
    private NavigationView mNav_view;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
      /*  Fade fade = new Fade();
        fade.setDuration(3000);
        getWindow().setEnterTransition(fade);*/

       /* Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setEnterTransition(slide);*/

       /* Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);*/

        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initBottomNavigation();
        initFirstFragment();
        showMaterialDialog();
    }

    private void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mDrawerLayout = (DrawerLayout) findViewById(drawerLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mNav_view = (NavigationView) findViewById(R.id.nav_view);

       /* View paddingView = findViewById(R.id.paddingView);
        ViewGroup.LayoutParams params = paddingView.getLayoutParams();
        params.height = getStatusBarHeight();*/
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTv_title.setText(titleIds[0]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置导航栏打开

        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);//设置抽屉开关
        barDrawerToggle.syncState();
        // 如果想看到菜单滑动过程中，Toolbar的一些细节变化（菜单按钮）
        mDrawerLayout.addDrawerListener(barDrawerToggle);//侧拉菜单栏和 抽屉开关同步
        mNav_view.setNavigationItemSelectedListener(this); //设置左侧导航栏选择监听

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUnreadCount();
    }

    public void updateUnreadCount() {
        //获取所有的未读消息
       // int unreadMsgsCount = EMClient.getInstance().chatManager().getUnreadMsgsCount();
      int unreadMsgsCount = 0;
        if (unreadMsgsCount>99){
            mBadgeItem.setText("99+");
            mBadgeItem.show(true);
        }else if (unreadMsgsCount>0){
            mBadgeItem.setText(unreadMsgsCount+"");
            mBadgeItem.show(true);
        }else{
            mBadgeItem.hide(true);
        }
    }

    private void initBottomNavigation() {
        /**
         * MODE_DEFAULT
         如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         MODE_FIXED
         填充模式，未选中的Item会显示文字，没有换挡动画。
         MODE_SHIFTING
         换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//设置背景风格
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//设置样式

        BottomNavigationItem conversationItem = new BottomNavigationItem(R.mipmap.ic_nav_homepage, titleIds[0]);
        mBadgeItem = new BadgeItem();
        mBadgeItem.setGravity(Gravity.RIGHT);
        mBadgeItem.setTextColor(getResources().getColor(R.color.white)); //设置徽章的颜色 背景
        mBadgeItem.setBackgroundColor(getResources().getColor(R.color.red));
        mBadgeItem.setText("");
        mBadgeItem.show();
        conversationItem.setBadgeItem(mBadgeItem);//绑定徽章
        mBottomNavigationBar.addItem(conversationItem);

        BottomNavigationItem contactItem = new BottomNavigationItem(R.mipmap.home_title_app, titleIds[1]);
        mBottomNavigationBar.addItem(contactItem);

        BottomNavigationItem pluginItem = new BottomNavigationItem(R.mipmap.home_title_android, titleIds[2]);
        mBottomNavigationBar.addItem(pluginItem);

        BottomNavigationItem home_four = new BottomNavigationItem(R.mipmap.home_title_ios, titleIds[3]);
        mBottomNavigationBar.addItem(home_four);

        BottomNavigationItem home_five = new BottomNavigationItem(R.mipmap.ic_nav_login, titleIds[4]);
        mBottomNavigationBar.addItem(home_five);

        //  mBottomNavigationBar.setActiveColor(R.color.btn_normal);//选中的颜色  (只需要在这里设置一次,就不用每次都设置了)
        // mBottomNavigationBar.setInActiveColor(R.color.inActive);//没选中的颜色
        mBottomNavigationBar.initialise(); //初始化
        mBottomNavigationBar.setTabSelectedListener(this); //设置监听
        // mBottomNavigationBar.setFirstSelectedPosition(1); //设置默认被选中的位置 默认是0
    }

    /**
     * 创建 fragment  解决重影问题
     */
    private void initFirstFragment() {
        /**重影  的Fragment
         * 如果这个Activity中已经有（就是Activity保存的历史的状态，又恢复了）老的Fragment，先全部移除
         * 重新加载过了 fragment  在onCreate(Bundle savedInstanceState) 的savedInstanceState里面保存了数据 activity又没有销毁
         * 这个我们重新启动这个activity 就会重新走onCreate, 就会重新重影
         */
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < titleIds.length; i++) {
            Fragment fragment = supportFragmentManager.findFragmentByTag(i + ""); //用tag 找到记录每个fragment
            if (fragment != null)    //判断下每个fragment是否已经创建过了,如果已经创建过了就移除掉
                fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commit();//提交事务
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(0), "0").commit();
        mTv_title.setText(titleIds[0]); //设置标题
    }

    /**
     * BottomNavigationBar 状态 选中的
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        /**
         * 先判断当前Fragment是否被添加到了MainActivity中
         * 如果添加了则直接显示即可
         * 如果没有添加则添加，然后显示
         */
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = FragmentFactory.getFragment(position);//根据bottom点击的位置获取对应的fragment布局
        MyLogger.e(TAG, "==fragment.isAdded()==" + fragment.isAdded());
        if (!fragment.isAdded())
            fragmentTransaction.add(R.id.fl_content, fragment, "" + position);//判断当前视图是否已经添加如果没有添加就 添加  同时添加tag 做标记
        fragmentTransaction.show(fragment).commit();
        mTv_title.setText(titleIds[position]); //设置标题
    }

    /**
     * 未选中的隐藏掉视图
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {
        getSupportFragmentManager().beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    /**
     * 又选择了 不做操作
     *
     * @param position
     */
    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 设置侧拉导航栏 选择监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);//设置菜单选中状态
        mDrawerLayout.closeDrawers(); //关闭菜单
        return false;
    }

    /**
     * 右边 菜单  默认图标不显示
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
        MenuBuilder builder = (MenuBuilder) menu; //显示图片
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
            case R.id.menu_add_friend: //添加好友
                break;
            case R.id.menu_scan:
                ToastUtils.showToast(this, "分享好友");
                break;
            case R.id.menu_about:
                ToastUtils.showToast(this, "关于我们");
                break;
            case android.R.id.home://导航栏 返回键  因为和侧拉菜单同步了 此方法没有调用
                // finish();
                break;
        }
        return true;
    }

    /**
     * 监听返回--是否退出程序
     */
    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序！");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
