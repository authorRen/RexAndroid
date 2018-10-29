package com.rex.rexandroid.fragment;

import com.rex.rexandroid.R;
import com.rex.rexandroid.component.CommonLazyFragment;

/**
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class MineFragment extends CommonLazyFragment {

    public static MineFragment newInstance() {
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
