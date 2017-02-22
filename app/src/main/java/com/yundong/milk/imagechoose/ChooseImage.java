package com.yundong.milk.imagechoose;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yundong.milk.imagechoose.utils.DisplayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class ChooseImage {

	// 选择图片类
	public static void jumpSelectImage(Activity context, int resultCode) {
		Intent intent = new Intent(context, MultiImageSelectorActivity.class);
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_TEXT, false);// 显示文本
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE); // 选择模式
		context.startActivityForResult(intent, resultCode);
	}

	/**
	 * 根据文件获取图片
	 * 
	 * @param file
	 * @return
	 */
	public static Bitmap getLoacalBitmap(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据url获取图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 计算图片的缩放值
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		int width = DisplayUtils.getDisplayWidth();
		int height = width * 3 / 5;
		options.inSampleSize = calculateInSampleSize(options, width, height);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}
	
	public static Bitmap getHeadmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		options.inSampleSize = calculateInSampleSize(options, 480, 320);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

}
