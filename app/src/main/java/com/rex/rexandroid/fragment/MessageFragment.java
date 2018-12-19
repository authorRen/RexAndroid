package com.rex.rexandroid.fragment;

import com.rex.rexandroid.R;
import com.rex.rexandroid.component.fragment.CommonLazyFragment;

/**
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class MessageFragment extends CommonLazyFragment {

    public static MessageFragment newInstance() {
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
