package com.yundong.milk.main.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;
import com.yundong.milk.util.ToastUtil;

/**
 * Created by lj on 2016/12/23.
 * 忘记密码之界面二
 */
public class ForgetPwdSecondActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd_second);
        initTitle(R.string.forgetPwd, true);
        findViewById(R.id.btnComplete).setOnClickListener(this);
        if (!CacheActivity.activityList.contains(ForgetPwdSecondActivity.this)) {
            CacheActivity.addActivity(ForgetPwdSecondActivity.this);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnComplete:
                String password = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
                if (TextUtils.isEmpty(password)){
                    ToastUtil.showShortToast(R.string.please_set_login_pwd);
                }else if (password.length() < 6){
                    ToastUtil.showShortToast(R.string.password_require);
                }else {
                    finish();
                    CacheActivity.finishSingleActivityByClass(ForgetPwdFirstActivity.class);
                }
                break;
        }
    }
}
