package com.rex.baselibrary.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 验证码倒计时
 *
 * @author Ren ZeQiang
 * @since 2018/10/26.
 */
public class CountdownView extends AppCompatTextView implements Runnable {


    private int mTotalTime = 60;    //倒计时秒数
    private static final String TIME_UNIT = "S";    //秒数单位文本

    private int mCurrentTime;   //当前秒数
    private CharSequence mRecordText;   //记录原有的文本

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置倒计时总秒数
     */
    public void setTotalTime(int totalTime) {
        this.mTotalTime = totalTime;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除延迟任务，避免内存泄露
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        mRecordText = getText();
        setEnabled(false);
        mCurrentTime = mTotalTime;
        post(this);
        return click;

    }

    @Override
    public void run() {
        mCurrentTime--;
        if (mCurrentTime == 0) {
            setText(mRecordText);
            setEnabled(true);
        } else {
            setText(new StringBuilder().append(mCurrentTime).append("\t").append(TIME_UNIT).toString());
            postDelayed(this, 1000);
        }
    }
}
