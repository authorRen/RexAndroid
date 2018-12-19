package com.rex.rexandroid.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @author huoguangxu
 * @since 2017/12/26.
 */

public class ClickTextSpan extends ClickableSpan {
    private TextPaint mTextPaint;
    @ColorInt
    private int mTextColor = Color.RED;
    /** 超链接形式的下划线，false 表示不显示下划线，true表示显示下划线 */
    private boolean mUnderlineEnable = true;
    private OnSpanTextClickListener mOnSpanTextClickListener;

    public ClickTextSpan(TextPaint textPaint) {
        this.mTextPaint = textPaint;
    }

    public ClickTextSpan(@ColorInt int textColor) {
        this.mTextColor = textColor;
    }

    public ClickTextSpan(@ColorInt int textColor, boolean underlineEnable) {
        this.mTextColor = textColor;
        this.mUnderlineEnable = underlineEnable;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        if (mTextPaint != null) {
            ds.set(mTextPaint);
        } else {
            ds.setColor(mTextColor);
            ds.setUnderlineText(mUnderlineEnable);
        }
    }

    @Override
    public void onClick(View widget) {
        if (mOnSpanTextClickListener != null) {
            mOnSpanTextClickListener.onTextClick(widget);
        }
    }

    public void setOnSpanTextClickListener(OnSpanTextClickListener listener) {
        mOnSpanTextClickListener = listener;
    }

    public void removeOnSpanTextClickListener() {
        mOnSpanTextClickListener = null;
    }

    public interface OnSpanTextClickListener {
        void onTextClick(View widget);
    }
}
