package com.rex.rexandroid.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.rex.baselibrary.base.BaseFragmentPagerAdapter;
import com.rex.rexandroid.component.CommonLazyFragment;
import com.rex.rexandroid.fragment.FindFragment;
import com.rex.rexandroid.fragment.HomeFragment;
import com.rex.rexandroid.fragment.MessageFragment;
import com.rex.rexandroid.fragment.MineFragment;

import java.util.List;

/**
 * 主页面的adapter
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class HomeViewPagerAdapter extends BaseFragmentPagerAdapter<CommonLazyFragment> {

    public HomeViewPagerAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void init(FragmentManager fm, List<CommonLazyFragment> mFragments) {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mFragments.add(MessageFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
    }
}
