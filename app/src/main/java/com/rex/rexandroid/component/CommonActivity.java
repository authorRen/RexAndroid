package com.rex.rexandroid.component;

import android.content.pm.ActivityInfo;

import com.rex.baselibrary.base.BaseActivity;

/**
 * app中Activity基类
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public abstract class CommonActivity extends BaseActivity {

    @Override
    protected void initView() {
        initOrientation();
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        //如果没有指定屏幕方向，则默认为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
