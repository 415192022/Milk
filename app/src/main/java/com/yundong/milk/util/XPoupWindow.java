package com.yundong.milk.util;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.yundong.milk.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by lx on 2017/2/17.
 */

public class XPoupWindow extends BasePopupWindow implements View.OnClickListener{
    private TextView tv_TakeHeader;
    private TextView tv_HeaderFromSD;
    private TextView tv_Cancel;
    private Activity activity;

    public XPoupWindow(Activity context) {
        super(context);
        activity=context;
        tv_TakeHeader= (TextView) findViewById(R.id.tv_TakeHeader);
        tv_HeaderFromSD= (TextView) findViewById(R.id.tv_HeaderFromSD);
        tv_Cancel= (TextView) findViewById(R.id.tv_Cancel);
        setViewClickListener(this,tv_TakeHeader,tv_HeaderFromSD,tv_Cancel);
    }

    @Override
    protected Animation initShowAnimation() {
//        AnimationSet set=new AnimationSet(false);
//        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        shakeAnima.setInterpolator(new CycleInterpolator(5));
//        shakeAnima.setDuration(400);
//        set.addAnimation(getDefaultAlphaAnimation());
//        set.addAnimation(shakeAnima);
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popupwindow_choose_photo);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.rl_pop_choose_root);
    }
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    // 请求缩略图信号标识
    private static int REQUEST_THUMBNAIL = 2;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_TakeHeader:
                ToastUtil.showShortToast("拍照");
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 启动相机
                activity.startActivityForResult(intent1, REQUEST_THUMBNAIL);
                dismiss();
                break;
            case R.id.tv_HeaderFromSD:
                ToastUtil.showShortToast("相册");
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(intent, IMAGE);
                dismiss();
                break;
            case R.id.tv_Cancel:
                dismiss();
                break;
        }

    }
}
