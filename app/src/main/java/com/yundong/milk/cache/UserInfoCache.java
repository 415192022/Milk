package com.yundong.milk.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.yundong.milk.user.model.UserInfoModel;


/**
 * Created by lj on 2016/10/31.
 * 缓存用户信息
 * 缓存在首选项
 */
public class UserInfoCache extends Cache {

    private static UserInfoModel mUser;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        String userId = getCurrentUserId();
        if (!userId.equals("-1")) {
            mUser = getUser(String.valueOf(userId));
        } else {
            mUser = new UserInfoModel();
        }
    }

    //用户是否为登陆状态
    public static boolean isLogin() {
        if (!getCurrentUserId().equals("-1")) {
            return true;
        }
        return false;
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    public static String getCurrentUserId() {
        SharedPreferences preferences = mContext.getSharedPreferences(CacheConfig.APP_CONFIG, 0);
        return preferences.getString("user_id", "-1");
    }

    /**
     * 设置当前用户
     *
     * @param userId
     */
    public static void setCurrentUserId(String userId) {
        SharedPreferences preferences = mContext.getSharedPreferences(CacheConfig.APP_CONFIG, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", userId);
        editor.apply();
    }

    //获取当前用户对象
    public static UserInfoModel getCurrentUser() {
        return mUser;
    }

    // 保存登录token值
//    public static void saveToken(String token){
//        SharedPreferences preferences = mContext.getSharedPreferences("token", mContext.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("token",token);
//        editor.apply();
//    }
//
//    // 获取token值
//    public static String getToken(){
//        SharedPreferences preferences = mContext.getSharedPreferences("token", mContext.MODE_PRIVATE);
//        return preferences.getString("token", "");
//    }

    //保存登陆信息
    public static void saveLoginInfo(String userId) {
        SharedPreferences preferences = mContext.getSharedPreferences(userId, 0);
        SharedPreferences.Editor editor = preferences.edit();
        if (!TextUtils.isEmpty(mUser.username)) {
            editor.putString("username", mUser.username);
        }
        if (!TextUtils.isEmpty(mUser.coin)) {
            editor.putString("coin", mUser.coin);
        }
        if (!TextUtils.isEmpty(mUser.mobile)) {
            editor.putString("mobile", mUser.mobile);
        }
        if (!TextUtils.isEmpty(mUser.avatar)){
            editor.putString("avatar", mUser.avatar);
        }
        editor.putString("id", userId);
        editor.apply();
    }

    /**
     * 获取用户id
     *
     * @param key 文件名
     * @return
     */
    private static UserInfoModel getUser(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(key, 0);
        UserInfoModel user = new UserInfoModel();
        user.id = getCurrentUserId();
        user.username = preferences.getString("username", "");
        user.coin = preferences.getString("coin", "");
        user.mobile = preferences.getString("mobile", "");
        user.avatar = preferences.getString("avatar", "");
        return user;
    }

    // 保存用户名
    public static void saveUserName(){
        SharedPreferences preferences = mContext.getSharedPreferences(getCurrentUserId(), 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", mUser.username);
        editor.apply();
    }
}
