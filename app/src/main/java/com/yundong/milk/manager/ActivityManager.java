package com.yundong.milk.manager;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Activity  Manager
 * Created by lj on 2016/10/31
 */
public final class ActivityManager {

    //save all activity
    private Set<Activity> mActivities = new HashSet<>();

    //current activity
    private Activity mCurrentActivity = null;

    //The hungry man type single mode
    private static ActivityManager sInstance = new ActivityManager();

    /**
     * get ActivityManager instance
     * @return ActivityManager实例
     */
    public static ActivityManager getInstance() {
        return sInstance;
    }

    private ActivityManager() {
    }

    /**
     * 当Activity执行onCreate时调用 - 保存启动的Activity
     * @param activity 执行onCreate的Activity
     */
    public void onCreate(Activity activity) {
        mActivities.add(activity);
    }

    /**
     * 当Activity执行onDestroy时调用 - 移除销毁的Activity
     * @param activity 执行onDestroy时的Activity
     */
    public void onDestroy(Activity activity) {
        mActivities.remove(activity);
    }

    /**
     * 关闭所有activity
     */
    public void finishActivities() {
        for (Activity activity : mActivities) {
            activity.finish();
        }
        mActivities.clear();
    }

    /**
     * 当Activity执行onResume时调用 - 保存当前显示的activity，更新栈顶Activity
     * @param activity 执行onResume的Activity
     */
    public void onResume(Activity activity) {
        mCurrentActivity = activity;
    }

    /**
     * 当Activity执行onPause时调用 - 清除当前显示的Activity
     */
    public void onPause() {
        mCurrentActivity = null;
    }

    /**
     * 获取当前显示的Activity
     * @return 当前显示的Activity，可能为空
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * get all Activities
     * @return Activities
     */
    public Set<Activity> getActivities() {
        return mActivities;
    }

}
