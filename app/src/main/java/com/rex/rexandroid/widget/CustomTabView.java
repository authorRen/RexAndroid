package com.rex.rexandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rex.rexandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义底部tab
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class CustomTabView extends LinearLayout implements View.OnClickListener {
    private List<View> mTabViews;   //保存TabView
    private List<Tab> mTabs;    //保存tab
    private OnTabCheckListener mOnTabCheckListener;

    public void setOnTabCheckListener(OnTabCheckListener onTabCheckListener) {
        this.mOnTabCheckListener = onTabCheckListener;
    }

    public CustomTabView(Context context) {
        super(context);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<>();
    }

    /**
     * 添加tab
     *
     * @param tab tab
     */
    public void addTab(Tab tab) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_item_layout, null);
        TextView textView = view.findViewById(R.id.custom_tab_text);
        ImageView imageView = view.findViewById(R.id.custom_tab_icon);
        imageView.setImageResource(tab.mIconNormalResId);
        textView.setText(tab.mText);
        textView.setTextColor(tab.mNormalColor);

        view.setTag(mTabViews.size());
        view.setOnClickListener(this);

        mTabViews.add(view);
        mTabs.add(tab);

        addView(view);
    }

    /**
     * 设置选中的Tab
     *
     * @param position 选中的position
     */
    public void setCurrentItem(int position) {
        if (position >= mTabs.size() || position < 0) {
            position = 0;
        }
        mTabViews.get(position).performClick();

        updateStatus(position);
    }

    /**
     * 更新状态
     *
     * @param position 更新的position
     */
    private void updateStatus(int position) {
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            TextView textView = view.findViewById(R.id.custom_tab_text);
            ImageView imageView = view.findViewById(R.id.custom_tab_icon);
            if (i == position) {
                imageView.setImageResource(mTabs.get(i).mIconPressedResId);
                textView.setTextColor(mTabs.get(i).mSelectColor);
            } else {
                imageView.setImageResource(mTabs.get(i).mIconNormalResId);
                textView.setTextColor(mTabs.get(i).mNormalColor);
            }
        }
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnTabCheckListener != null) {
            mOnTabCheckListener.onTabSelected(v, position);
        }

        updateStatus(position);
    }


    public interface OnTabCheckListener {
        void onTabSelected(View v, int position);
    }

    public static class Tab {
        private int mIconNormalResId;
        private int mIconPressedResId;
        private int mNormalColor;
        private int mSelectColor;
        private String mText;

        public Tab setmIconNormalResId(int mIconNormalResId) {
            this.mIconNormalResId = mIconNormalResId;
            return this;
        }

        public Tab setmIconPressedResId(int mIconPressedResId) {
            this.mIconPressedResId = mIconPressedResId;
            return this;
        }

        public Tab setmNormalColor(int mNormalColor) {
            this.mNormalColor = mNormalColor;
            return this;
        }

        public Tab setmSelectColor(int mSelectColor) {
            this.mSelectColor = mSelectColor;
            return this;
        }

        public Tab setmText(String mText) {
            this.mText = mText;
            return this;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 调整每个Tab的大小
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabs.size());
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

            view.setLayoutParams(params);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabViews != null) {
            mTabViews.clear();
        }
        if (mTabs != null) {
            mTabs.clear();
        }
    }
}
