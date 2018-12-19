package com.rex.rexandroid.component.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.rex.rexandroid.R;

import java.util.List;

/**
 * 启动页面
 *
 * @author Ren ZeQiang
 * @since 2018/10/30.
 */
public class LauncherActivity extends CommonActivity implements Animation.AnimationListener, OnPermission {

    private static final int ANIM_TIME = 1000;
    private ImageView mImageBg;
    private ImageView mImageIcon;
    private TextView mNameTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initView() {
        mImageBg = findViewById(R.id.iv_launcher_bg);
        mImageIcon = findViewById(R.id.iv_launcher_icon);
        mNameTv = findViewById(R.id.iv_launcher_name);
        //初始化动画
        initStartAnim();
    }

    /**
     * 启动动画
     */
    private void initStartAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f, 1.0f);
        alphaAnimation.setDuration(ANIM_TIME * 2);
        alphaAnimation.setAnimationListener(this);
        mImageBg.setAnimation(alphaAnimation);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIM_TIME);
        mImageIcon.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(ANIM_TIME);
        mNameTv.startAnimation(ra);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (XXPermissions.isHasPermission(LauncherActivity.this, Permission.Group.STORAGE)) {
            hasPermission(null, true);
        }else {
            requestFilePermission();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        requestFilePermission();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void hasPermission(List<String> granted, boolean isAll) {
        startActivity(HomeActivity.class);
        finish();
    }

    @Override
    public void noPermission(List<String> denied, boolean quick) {
        if (quick) {
            ToastUtils.show("没有权限访问文件，请手动授予权限");
            XXPermissions.gotoPermissionSettings(LauncherActivity.this, true);
        }else {
            ToastUtils.show("请先授予文件读写权限");
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestFilePermission();
                }
            }, 2000);
        }
    }

    private void requestFilePermission() {
        XXPermissions.with(this)
                .permission(Permission.Group.STORAGE)
                .request(this);
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
//        super.onBackPressed();
    }

    @Override
    protected void initOrientation() {
        super.initOrientation();
    }
}
