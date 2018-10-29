package com.rex.rexandroid.component;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.rex.baselibrary.ui.NoScrollViewPager;
import com.rex.rexandroid.R;
import com.rex.rexandroid.adapter.HomeViewPagerAdapter;
import com.rex.rexandroid.widget.CustomTabView;


public class HomeActivity extends CommonActivity implements CustomTabView.OnTabCheckListener,
        ViewPager.OnPageChangeListener {

    private NoScrollViewPager mNoScrollViewPager;
    private CustomTabView mCustomTabView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mNoScrollViewPager = findViewById(R.id.home_view_pager);
        mNoScrollViewPager.setAdapter(new HomeViewPagerAdapter(this));
        // 限制页面数量
        mNoScrollViewPager.setOffscreenPageLimit(4);
        mNoScrollViewPager.addOnPageChangeListener(this);
        mCustomTabView = findViewById(R.id.home_custom_tab_view);
        initTabs();
    }

    @Override
    protected void initData() {

    }

    private void initTabs() {
        CustomTabView.Tab homeTab = new CustomTabView.Tab().setmText("首页")
                .setmIconNormalResId(R.mipmap.tab_ico_home_off)
                .setmIconPressedResId(R.mipmap.tab_ico_home)
                .setmNormalColor(R.color.white60)
                .setmSelectColor(R.color.colorPrimary);
        mCustomTabView.addTab(homeTab);

        CustomTabView.Tab findTab = new CustomTabView.Tab().setmText("发现")
                .setmIconNormalResId(R.mipmap.tab_ico_found_off)
                .setmIconPressedResId(R.mipmap.tab_ico_found)
                .setmNormalColor(R.color.white60)
                .setmSelectColor(R.color.colorPrimary);
        mCustomTabView.addTab(findTab);

        CustomTabView.Tab messageTab = new CustomTabView.Tab().setmText("消息")
                .setmIconNormalResId(R.mipmap.tab_ico_message_off)
                .setmIconPressedResId(R.mipmap.tab_ico_message)
                .setmNormalColor(R.color.white60)
                .setmSelectColor(R.color.colorPrimary);
        mCustomTabView.addTab(messageTab);

        CustomTabView.Tab mineTab = new CustomTabView.Tab().setmText("我的")
                .setmIconNormalResId(R.mipmap.tab_ico_me_off)
                .setmIconPressedResId(R.mipmap.tab_ico_me)
                .setmNormalColor(R.color.white60)
                .setmSelectColor(R.color.colorPrimary);
        mCustomTabView.addTab(mineTab);
        //设置监听
        mCustomTabView.setOnTabCheckListener(this);
        //设置默认选项
        mCustomTabView.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(View v, int position) {
        onTabItemSelected(position);
    }

    private void onTabItemSelected(int position) {
        switch (position) {
            case 0:
                mNoScrollViewPager.setCurrentItem(0);
                break;
            case 1:
                mNoScrollViewPager.setCurrentItem(1);
                break;
            case 2:
                mNoScrollViewPager.setCurrentItem(2);
                break;
            case 3:
                mNoScrollViewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int position) {
        mCustomTabView.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
