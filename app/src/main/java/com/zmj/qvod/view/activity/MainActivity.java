package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.ll_title_menu)
    FrameLayout llTitleMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.iv_title_video)
    ImageView ivTitleVideo;
    @BindView(R.id.iv_title_special)
    ImageView ivTitleSpecial;
    @BindView(R.id.iv_title_mine)
    ImageView ivTitleMine;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();

        initListener();
    }

    private void initListener() {
        vpFragment.setOffscreenPageLimit(2);
        //
        vpFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ivTitleSpecial.setSelected(true);
                        ivTitleVideo.setSelected(false);
                        ivTitleMine.setSelected(false);
                        break;
                    case 1:
                        ivTitleVideo.setSelected(true);
                        ivTitleSpecial.setSelected(false);
                        ivTitleMine.setSelected(false);
                        break;
                    case 2:
                        ivTitleMine.setSelected(true);
                        ivTitleVideo.setSelected(false);
                        ivTitleSpecial.setSelected(false);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //
        navView.setNavigationItemSelectedListener(this);
    }

    public void initView() {
        //ToolBar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            //去除返回键显示
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        //
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawerLayout, ContextCompat.getColor(this, R.color.colorTheme));
        //
        ivTitleSpecial.setSelected(true);
    }

    public ViewPager getViewPager() {
        return vpFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.ll_title_menu, R.id.iv_title_video, R.id.iv_title_special, R.id.iv_title_mine, R.id.fb_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_title_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_special:
                ivTitleSpecial.setSelected(true);
                ivTitleVideo.setSelected(false);
                ivTitleMine.setSelected(false);
                if (vpFragment.getCurrentItem() != 0)
                    vpFragment.setCurrentItem(0);
                break;
            case R.id.iv_title_video:
                ivTitleVideo.setSelected(true);
                ivTitleSpecial.setSelected(false);
                ivTitleMine.setSelected(false);
                if (vpFragment.getCurrentItem() != 1)
                    vpFragment.setCurrentItem(1);
                break;
            case R.id.iv_title_mine:
                ivTitleMine.setSelected(true);
                ivTitleVideo.setSelected(false);
                ivTitleSpecial.setSelected(false);
                if (vpFragment.getCurrentItem() != 2)
                    vpFragment.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.nav_about:
                AboutActivity.startAction(this);
                break;
            case R.id.nav_pic:
                ivTitleMine.setSelected(true);
                ivTitleVideo.setSelected(false);
                ivTitleSpecial.setSelected(false);
                if (vpFragment.getCurrentItem() != 2)
                    vpFragment.setCurrentItem(2);
                break;
            case R.id.nav_main:
                ivTitleSpecial.setSelected(true);
                ivTitleVideo.setSelected(false);
                ivTitleMine.setSelected(false);
                if (vpFragment.getCurrentItem() != 0)
                    vpFragment.setCurrentItem(0);
                break;
            case R.id.nav_search:
                JUtils.Toast("敬请期待···");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                //后台模式
                moveTaskToBack(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
