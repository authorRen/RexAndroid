package com.rex.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rex.baselibrary.util.KeyboardUtils;

/**
 * Activity基类
 *
 * @author Ren ZeQiang
 * @since 2018/10/25.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }

        init();
    }

    private void init() {
        initView();
        initData();
    }

    //引入布局
    protected abstract int getLayoutId();
    //初始化控件
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();

    @Override
    public void finish() {
        //隐藏软键盘，避免软键盘引起的内存泄漏
        KeyboardUtils.hideKeyboard(getCurrentFocus());
        super.finish();
    }

    /**
     * 跳转到其他activity
     *
     * @param cls   目标activity的Class
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 延迟执行某个任务
     *
     * @param action    Runnable对象
     */
    public boolean post(Runnable action) {
        return getWindow().getDecorView().post(action);
    }

    /**
     * 延迟某个时间执行某个任务
     *
     * @param action    Runnable对象
     * @param delayMillis   延迟的时间
     */
    public boolean postDelay(Runnable action, long delayMillis) {
        return getWindow().getDecorView().postDelayed(action, delayMillis);
    }

    /**
     * 删除某个延迟任务
     * @param action        Runnable对象
     */
    public boolean removeCallbacks(Runnable action) {
        return getWindow().getDecorView().removeCallbacks(action);
    }
}
