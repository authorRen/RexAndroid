package com.rex.baselibrary.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter基类
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public abstract class BaseFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragments = new ArrayList<>(); //Fragment集合

    private T mCurrentFragment; //当前显示的Fragment

    /**
     * 在Activity中使用ViewPager适配器
     */
    public BaseFragmentPagerAdapter(FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    /**
     * 在Fragment中使用ViewPager适配器
     */
    public BaseFragmentPagerAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }


    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        init(fm, mFragments);
    }

    //初始化Fragment
    protected abstract void init(FragmentManager fm, List<T> mFragments);

    @Override
    public T getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            //记录当前的fragment对象
            mCurrentFragment = (T) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 获取Fragment集合
     */
    public List<T> getAllFragment() {
        return mFragments;
    }

    /**
     * 获取当前的Fragment
     */
    public T getCurrentFragment() {
        return mCurrentFragment;
    }
}
