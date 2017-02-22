package com.yundong.milk.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.manager.ActivityManager;
import com.yundong.milk.util.SystemBarTintManager;
import com.yundong.milk.util.ToastUtil;

/**
 * Created by lj on 2016/10/31.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBar(true);
        ActivityManager.getInstance().onCreate(this);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void changeStatusBar(boolean flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (flag) {
                setTranslucentStatus(true);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(flag);
                tintManager.setStatusBarTintResource(R.color.colorPrimary);
            } else {
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(flag);
            }
        }
    }

    /**
     * 初始化标题
     *
     * @param resTitle
     * @param showBack
     * @param imageRight
     */
    public void initTitle(int resTitle, boolean showBack, int imageRight, View.OnClickListener rightListener) {
        TextView titleText = (TextView) findViewById(R.id.label_title);
        titleText.setText(resTitle);
        if (showBack) {
            ImageView left = (ImageView) findViewById(R.id.imageLeft);
            left.setVisibility(View.VISIBLE);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (imageRight != -1) {
            ImageView right = (ImageView) findViewById(R.id.imageRight);
            right.setVisibility(View.VISIBLE);
            right.setImageResource(imageRight);
            right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题
     *
     * @param resTitle
     * @param imageLeft
     */
    public void initTitleMenu(int resTitle, int imageLeft, View.OnClickListener rightListener) {
        TextView titleText = (TextView) findViewById(R.id.label_title);
        titleText.setText(resTitle);
        ImageView right = (ImageView) findViewById(R.id.imageLeft);
        right.setVisibility(View.VISIBLE);
        right.setImageResource(imageLeft);
        right.setOnClickListener(rightListener);
    }

    /**
     * 初始化标题
     *
     * @param resTitle
     * @param showBack
     */
    public void initTitle(String resTitle, boolean showBack) {
        TextView titleText = (TextView) findViewById(R.id.label_title);
        titleText.setText(resTitle);
        if (showBack) {
            ImageView left = (ImageView) findViewById(R.id.imageLeft);
            left.setVisibility(View.VISIBLE);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void initTitle(int resTitle, boolean showBack, String resRight, View.OnClickListener rightListener) {
        TextView titleText = (TextView) findViewById(R.id.label_title);
        titleText.setText(resTitle);
        if (showBack) {
            ImageView left = (ImageView) findViewById(R.id.imageLeft);
            left.setVisibility(View.VISIBLE);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        TextView right = (TextView) findViewById(R.id.txtRight);
        right.setVisibility(View.VISIBLE);
        right.setText(resRight);
        right.setOnClickListener(rightListener);
    }

    public void initTitle(int resTitle, boolean showBack) {
        initTitle(resTitle, showBack, -1, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager.getInstance().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().onDestroy(this);
    }


    public void errorMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    public void noNetWork() {
        ToastUtil.showShortToast(R.string.net_not_available);
    }

    @Override
    public void onClick(View view) {

    }
}
