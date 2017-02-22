package com.yundong.milk.imagechoose.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;

/**
 * 屏幕工具类
 * Created by lj on 2016.10.31
 */
public class DisplayUtils {

	private static DisplayMetrics mDisplayMetrics;

	public static void init(Context context) {
		mDisplayMetrics = context.getResources().getDisplayMetrics();
	}

    public static int sp2px(int spValue) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, mDisplayMetrics);
    }

    public static int getDisplayWidth() {
        return mDisplayMetrics.widthPixels;
    }

    public static int getDisplayHeight() {
        return mDisplayMetrics.heightPixels;
    }

    public static int dp2px(float dp) {
		return (int)(mDisplayMetrics.density * dp + 0.5f);
	}

    public static int px2dip(int px) {
        return (int) (px / mDisplayMetrics.density + 0.5f);
    }

    public static View getEmptyView(Context context, int msg, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.empty_view, null);
        ((TextView) view.findViewById(R.id.txt_msg)).setText("");
//      ((ImageView) view.findViewById(R.id.img_msg)).setImageResource();
        return view;
    }

    public static View getNotWorkView(Context context, int msg, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.empty_view, null);
        ((TextView) view.findViewById(R.id.txt_msg)).setText("");
//      ((ImageView) view.findViewById(R.id.img_msg)).setImageResource();
        return view;
    }

    //可以将一下方法封装到一个Utils工具类中
    /**
     * 把(Xml中)dp转换为像素px展现
     */
    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
    /**
     * 得到设备的(Xml中)dp
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}