package com.yundong.milk.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.api.MainApi;
import com.yundong.milk.api.URLConst;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CheckVerificationCodeBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.RegistBean;
import com.yundong.milk.model.RegisterCompleteLoginBean;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.present.LoginPresenter;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.MD5;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.view.ICheckVerificationView;
import com.yundong.milk.view.IGetVerificationView;
import com.yundong.milk.view.ILoginView;
import com.yundong.milk.view.IRegisterView;

import java.util.HashMap;

/**
 * Created by lj on 2016/12/23.
 * 登录,注册
 */
public class LoginActivity extends BaseActivity implements ILoginView, IGetVerificationView, ICheckVerificationView, IRegisterView {

    private TextView mTxtGetVerCode;
    private LoginPresenter loginPresenter;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.txtUserInfo).setOnClickListener(this);
        findViewById(R.id.txtRegister).setOnClickListener(this);
        findViewById(R.id.btnNext).setOnClickListener(this);
        findViewById(R.id.btnComplete).setOnClickListener(this);
        findViewById(R.id.txtForgetPwd).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);
        mTxtGetVerCode = (TextView) findViewById(R.id.txtGetVerCode);
        mTxtGetVerCode.setOnClickListener(this);
        findViewById(R.id.txtTriangleLogin).setVisibility(View.VISIBLE);
        loginPresenter = LoginPresenter.getInstance().with(this, this, this, this);
        loginPresenter.login(PreferencesUtils.getString(this, Const.LOGIN_NAME), PreferencesUtils.getString(this, Const.LOGIN_PWD));
    }
    String registerTwoPwd;
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtUserInfo: // 切换背景图的登录和注册
                findViewById(R.id.txtTriangleLogin).setVisibility(View.VISIBLE);
                findViewById(R.id.txtTriangleRegister).setVisibility(View.GONE);
                findViewById(R.id.layoutLogin).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutRegisterFirst).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutRegisterSecond).setVisibility(View.VISIBLE);
                break;
            case R.id.btnLogin://登录按钮
                String account = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
                password = ((EditText) findViewById(R.id.editPassword)).getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtil.showShortToast(R.string.please_input_account);
                } else if (TextUtils.isEmpty(password)) {
                    ToastUtil.showShortToast(R.string.please_input_login_password);
                } else {
                    loginPresenter.login(account, password);
                }


                break;
            case R.id.txtRegister: //切换背景图的登录和注册
                findViewById(R.id.txtTriangleLogin).setVisibility(View.GONE);
                findViewById(R.id.txtTriangleRegister).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutLogin).setVisibility(View.GONE);
                findViewById(R.id.layoutRegisterFirst).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutRegisterSecond).setVisibility(View.GONE);
                break;
            case R.id.txtGetVerCode: //注册界面一的获取验证码
                String phoneNum = ((EditText) findViewById(R.id.editPhone)).getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtil.showShortToast(R.string.please_input_phone_number);
                } else if (phoneNum.length() != 11) {
                    ToastUtil.showShortToast(R.string.phone_number_not_correct);
                } else {
                    timer.start();
                    loginPresenter.getVerification(phoneNum);
                }
                break;
            case R.id.btnNext: //注册界面一的下一步
                String phone = ((EditText) findViewById(R.id.editPhone)).getText().toString().trim();
                String sMSVerCode = ((EditText) findViewById(R.id.editSMSVerCode)).getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.showShortToast(R.string.please_input_phone_number);
                } else if (phone.length() != 11) {
                    ToastUtil.showShortToast(R.string.phone_number_not_correct);
                } else if (TextUtils.isEmpty(sMSVerCode)) {
                    ToastUtil.showShortToast(R.string.please_input_SMS_verification_code);
                } else {
                    //验证验证码正确性
                    CheckVerificationCodeBean checkVerificationCodeBean = new CheckVerificationCodeBean();
                    checkVerificationCodeBean.setMobile(phone);
                    checkVerificationCodeBean.setMsg(sMSVerCode);
                    loginPresenter.checkVerification(checkVerificationCodeBean);

                }
                break;
            case R.id.btnComplete: //注册界面二的完成按钮
                 registerTwoPwd = ((EditText) findViewById(R.id.editPwd)).getText().toString().trim();
                if (TextUtils.isEmpty(registerTwoPwd)) {
                    ToastUtil.showShortToast(R.string.please_set_login_pwd);
                } else if (registerTwoPwd.length() < 6) {
                    ToastUtil.showShortToast(R.string.password_require);
                } else {
                    loginPresenter.register(((EditText) findViewById(R.id.editPhone)).getText().toString().trim(), registerTwoPwd);
                    //debug
                    startActivity(new Intent(this, PerfectAddressActivity.class));
                }
                break;
            case R.id.txtForgetPwd: //登录界面的忘记密码
                startActivity(new Intent(this, ForgetPwdFirstActivity.class));
                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTxtGetVerCode.setText("剩(" + (millisUntilFinished / 1000) + "s)");
            mTxtGetVerCode.setEnabled(false);
            //请求验证码接口

        }

        @Override
        public void onFinish() {
            mTxtGetVerCode.setText(R.string.click_and_get_verifyCode);
            mTxtGetVerCode.setEnabled(true);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
        }
    }


    @Override
    public void login(LoginBean loginBean) {

        if (loginBean.getCode().equals("2000")) {
            ToastUtil.showShortToast(loginBean.getData().getUserinfo().getUname() + "登录成功");
            PreferencesUtils.putString(this, Const.LOGIN_NAME, loginBean.getData().getUserinfo().getUname());
            PreferencesUtils.putString(this, Const.LOGIN_PWD, password);
            PreferencesUtils.putString(this, Const.LOGIN_TOKEN, loginBean.getData().getToken());
            PreferencesUtils.putString(this, Const.LOGIN_AVATAR, loginBean.getData().getUserinfo().getAvatar());
            PreferencesUtils.putString(this, Const.LOGIN_PHONE, loginBean.getData().getUserinfo().getPhone());
            PreferencesUtils.putString(this, Const.LOGIN_ID, loginBean.getData().getUserinfo().getId());
            YunDongApplication.setLoginBean(loginBean);
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.pop_enter_anim, R.anim.basepopup_fade_out);
        } else if (loginBean.getCode().equals("3004")) {
            ToastUtil.showShortToast("密码错误");
        }
    }

    @Override
    public void loginOnError(String e) {
        ToastUtil.showShortToast("登录错误，请检查账号或密码是否正确或网络是否连接正常");
    }

    @Override
    public void getVerificationCode(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast("" + baseReceiveBean);
    }

    @Override
    public void getVerificationCodeOnError(String e) {
        ToastUtil.showShortToast("获取验证码失败");
    }

    @Override
    public void checkVerificationCode(BaseReceiveBean baseReceiveBean) {
        if (baseReceiveBean.getCode().equals("3003")) {
            ToastUtil.showShortToast("该手机号码已经注册，请尝试登录或找回密码。");
        } else if (baseReceiveBean.getCode().equals("3000")) {
            ToastUtil.showShortToast("验证码输入错误，请重新输入。");
        } else {
            findViewById(R.id.layoutLogin).setVisibility(View.GONE);
            findViewById(R.id.layoutRegisterFirst).setVisibility(View.GONE);
            findViewById(R.id.layoutRegisterSecond).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void checkVerificationCodeOnError(String e) {
        ToastUtil.showShortToast("验证码错误,请重新输入。");
    }

    @Override
    public void register(RegistBean registBean) {
        ToastUtil.showShortToast(registBean + "");
        if (registBean.getCode().equals("2000")) {
            ToastUtil.showShortToast("注册成功。");
            RegisterCompleteLoginBean registerCompleteLoginBean=new RegisterCompleteLoginBean();
            registerCompleteLoginBean.setRegistBean(registBean);
            registerCompleteLoginBean.setPwd(registerTwoPwd);
            RxBus.getDefault().post(registerCompleteLoginBean);
            startActivity(new Intent(this, PerfectAddressActivity.class));
            finish();
        } else {
            ToastUtil.showShortToast(registBean.getMsg());
        }

    }

    @Override
    public void registerOnError(String e) {
        ToastUtil.showShortToast("注册失败。");
    }
}
