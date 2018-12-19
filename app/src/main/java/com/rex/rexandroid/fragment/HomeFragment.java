package com.rex.rexandroid.fragment;

import android.os.Build;
import android.support.v7.widget.Toolbar;

import com.rex.rexandroid.R;
import com.rex.rexandroid.component.fragment.CommonLazyFragment;

/**
 * 首页
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class HomeFragment extends CommonLazyFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("首页");
        }

    }

    @Override
    public void initData() {

    }
}
