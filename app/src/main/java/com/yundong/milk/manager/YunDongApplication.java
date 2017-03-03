package com.yundong.milk.manager;

import android.app.Application;
import android.content.Intent;

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

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by lj on 2016/10/31.
 */
public class YunDongApplication extends Application {
    private static LoginBean loginBean = new LoginBean();
    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
//        NetworkTypeUtils.init(this);
        DisplayUtils.init(this);
        Cache.init(this);
        JsonUtil.init();
        ToastUtil.init(this);
        HDApp.init();
        ShareSDK.initSDK(this, "1b7b425d6ac0e");
        VolleyUtil.getVolleyUtil(this);


    }

    public static Application getApplication() {
        return application;
    }

    public static LoginBean getLoginBean() {
        return loginBean;
    }

    public static void setLoginBean(LoginBean loginBean) {
        YunDongApplication.loginBean = loginBean;
    }
}
