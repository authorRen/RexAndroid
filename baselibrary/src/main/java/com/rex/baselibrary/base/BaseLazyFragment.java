package com.rex.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Fragment懒加载基类
 *
 * @author Ren ZeQiang
 * @since 2018/10/26.
 */
public abstract class BaseLazyFragment extends Fragment {
    private boolean isLazyLoad = false; //是否已经懒加载
    private View mRootView; //根布局
    private Activity mActivity; //Activity对象


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    public Activity getSupportActivity() {
        return mActivity;
    }

    private boolean isVisibleToUser;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && !isLazyLoad() && getView() != null) {
            isLazyLoad = true;
            init();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isVisibleToUser && !isLazyLoad() && getView() != null) {
            isLazyLoad = true;
            init();
        }
    }

    private void init() {
        initView();
        initData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解决java.lang.IllegalStateException: Activity has been destroyed 的错误
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        mRootView = null;
    }

    //引入布局
    public abstract int getLayoutId();
    //初始化控件
    public abstract void initView();
    //初始化数据
    public abstract void initData();

    protected boolean isLazyLoad() {
        return isLazyLoad;
    }

    /**
     * 跳转到其他Activity
     *
     * @param cls          目标Activity的Class
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    /**
     * 根据资源id获取一个View
     */
    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }
}
