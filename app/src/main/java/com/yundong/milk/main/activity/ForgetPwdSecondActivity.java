package com.yundong.milk.main.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;
import com.yundong.milk.model.CheckVerificationCodeBean;
import com.yundong.milk.model.FindPwdBean;
import com.yundong.milk.present.ForgetPwdSecondActivityPresenter;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IFindPwdView;

/**
 * Created by lj on 2016/12/23.
 * 忘记密码之界面二
 */
public class ForgetPwdSecondActivity extends BaseActivity
        implements
        IFindPwdView {

    private ForgetPwdSecondActivityPresenter forgetPwdSecondActivityPresenter;

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    private CheckVerificationCodeBean checkVerificationCodeBean;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveFindPwdData(CheckVerificationCodeBean checkVerificationCodeBean) {
        this.checkVerificationCodeBean = checkVerificationCodeBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd_second);
        initTitle(R.string.forgetPwd, true);
        findViewById(R.id.btnComplete).setOnClickListener(this);
        if (!CacheActivity.activityList.contains(ForgetPwdSecondActivity.this)) {
            CacheActivity.addActivity(ForgetPwdSecondActivity.this);
        }

        forgetPwdSecondActivityPresenter = ForgetPwdSecondActivityPresenter.getInstance().with(this);
    }
    String password;
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnComplete:
                 password = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showShortToast(R.string.please_set_login_pwd);
                } else if (password.length() < 6) {
                    ToastUtil.showShortToast(R.string.password_require);
                } else {
                    if (null != checkVerificationCodeBean) {
                        forgetPwdSecondActivityPresenter.findPwd(checkVerificationCodeBean.getMobile(), password, checkVerificationCodeBean.getMsg());
                    }
                }
                break;
        }
    }


    //找回密码
    @Override
    public void findPwd(FindPwdBean findPwdBean) {
        if (findPwdBean.getCode().equals("2000")) {
            PreferencesUtils.putString(this, Const.LOGIN_PWD,password);
            finish();
            CacheActivity.finishSingleActivityByClass(ForgetPwdFirstActivity.class);
        } else {
            ToastUtil.showShortToast(findPwdBean.getMsg());
        }

    }

    @Override
    public void findPwdOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
