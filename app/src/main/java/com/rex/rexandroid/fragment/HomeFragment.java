package com.rex.rexandroid.fragment;

import com.rex.rexandroid.R;
import com.rex.rexandroid.component.CommonLazyFragment;

/**
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class HomeFragment extends CommonLazyFragment {

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();

        return homeFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
