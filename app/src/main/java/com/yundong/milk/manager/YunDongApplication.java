package com.yundong.milk.manager;

import android.app.Application;

import com.yundong.milk.cache.Cache;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.imagechoose.utils.DisplayUtils;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.util.JsonUtil;
import com.yundong.milk.util.ToastUtil;

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by lj on 2016/10/31.
 */
public class YunDongApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
//        NetworkTypeUtils.init(this);
        DisplayUtils.init(this);
        Cache.init(this);
        JsonUtil.init();
        ToastUtil.init(this);
        HDApp.init();
        ShareSDK.initSDK(this,"1b7b425d6ac0e");
        VolleyUtil.getVolleyUtil(this);
    }
}
