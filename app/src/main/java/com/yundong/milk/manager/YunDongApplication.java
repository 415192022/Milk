package com.yundong.milk.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.yundong.milk.R;
import com.yundong.milk.cache.Cache;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.imagechoose.utils.DisplayUtils;
import com.yundong.milk.interaptor.ILogin;
import com.yundong.milk.main.activity.LoginActivity;
import com.yundong.milk.main.activity.MainActivity;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.present.LoginPresenter;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.JsonUtil;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.ILoginView;

import java.util.Stack;

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by lj on 2016/10/31.
 */
public class YunDongApplication extends Application {
    private static LoginBean loginBean = new LoginBean();
    private static YunDongApplication application;

    private static Stack<Activity> activityStack;

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class cls) {
        if (activityStack.size() > 0) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    activityStack.remove(activity);
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public Activity getmActivity(Class cls) {
        Activity ac = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                ac = activity;
            }
        }
        return ac;
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        NetworkTypeUtils.init(this);
        DisplayUtils.init(this);
        Cache.init(this);
        JsonUtil.init();
        ToastUtil.init(this);
        HDApp.init();
        ShareSDK.initSDK(this, "1b7b425d6ac0e");
        VolleyUtil.getVolleyUtil(this);
        activityStack = new Stack<Activity>();

    }

    public static YunDongApplication getApplication() {
        return application;
    }

    public static LoginBean getLoginBean() {
        return loginBean;
    }

    public static void setLoginBean(LoginBean loginBean) {
        YunDongApplication.loginBean = loginBean;
    }
}
