package com.yundong.milk.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by lj on 2016/12/20.
 * 吐司封装
 */
public class ToastUtil {

    private static Context mContext;
    private static Resources mResources;

    public static void init(Context context){
        mContext = context;
        mResources = context.getResources();
    }

    public static void showShortToast(String msg) {
        showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(int strRes) {
        showShortToast(mResources.getString(strRes));
    }

    public static void showLongToast(String msg) {
        showToast(mContext, msg, Toast.LENGTH_LONG);
    }

    public static void showLongToast(int strRes) {
        showLongToast(mResources.getString(strRes));
    }

    public static void showToast(Context context, String msg, int duration){
        showToast(context, msg, duration, Gravity.BOTTOM);
    }
    public static void showToast(Context context, String msg, int duration,int gravity){
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 30);
        toast.show();
    }

}
