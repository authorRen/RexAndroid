package com.rex.rexandroid.fragment;

import com.rex.rexandroid.R;
import com.rex.rexandroid.component.CommonLazyFragment;


/**
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class FindFragment extends CommonLazyFragment {

    public static FindFragment newInstance() {
        FindFragment findFragment = new FindFragment();
        return findFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
