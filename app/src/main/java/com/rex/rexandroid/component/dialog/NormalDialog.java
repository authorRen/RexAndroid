package com.rex.rexandroid.component.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.rex.rexandroid.R;

/**
 * 确认/取消弹框
 *
 * @author Ren ZeQiang
 * @since 2018/12/18.
 */
public class NormalDialog extends Dialog {

    private final TextView cancelBtn;
    private final TextView confirmBtn;
    private NormalDialog dialog;
    private final TextView mContentTv;

    public NormalDialog(@NonNull final Context context) {
        super(context, R.style.NormalDialog);
        dialog = this;
        setContentView(R.layout.layout_normal_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        setCancelable(false);

        cancelBtn = findViewById(R.id.cancel);
        confirmBtn = findViewById(R.id.confirm);
        mContentTv = findViewById(R.id.tv_content);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public NormalDialog setCancelClick(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    public NormalDialog setContentMsg(CharSequence message) {
        mContentTv.setText(message);
        return this;
    }

    public NormalDialog setPositiveButton(CharSequence str, final OnClickListener listener) {
        if (str != null) {
            confirmBtn.setText(str);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
                dismiss();
            }
        });
        return this;
    }

    public NormalDialog setNegativeButton(CharSequence str, final OnClickListener listener) {
        if (str != null) {
            cancelBtn.setText(str);
        }
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
                dismiss();
            }
        });
        return this;
    }
}
