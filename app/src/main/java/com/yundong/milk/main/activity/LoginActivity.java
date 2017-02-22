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
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.util.MD5;
import com.yundong.milk.util.ToastUtil;

import java.util.HashMap;

/**
 * Created by lj on 2016/12/23.
 * 登录,注册
 */
public class LoginActivity extends BaseActivity {

    private TextView mTxtGetVerCode;

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
    }

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
                login("", "");
//                String account = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
//                String password = ((EditText) findViewById(R.id.editPassword)).getText().toString().trim();
//                if (TextUtils.isEmpty(account)) {
//                    ToastUtil.showShortToast(R.string.please_input_account);
//                } else if (TextUtils.isEmpty(password)) {
//                    ToastUtil.showShortToast(R.string.please_input_login_password);
//                } else {
//                    login(account, password);
//                }
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
                    findViewById(R.id.layoutLogin).setVisibility(View.GONE);
                    findViewById(R.id.layoutRegisterFirst).setVisibility(View.GONE);
                    findViewById(R.id.layoutRegisterSecond).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnComplete: //注册界面二的完成按钮
                String pwd = ((EditText) findViewById(R.id.editPwd)).getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showShortToast(R.string.please_set_login_pwd);
                } else if (pwd.length() < 6) {
                    ToastUtil.showShortToast(R.string.password_require);
                } else {
                    startActivity(new Intent(this, PerfectAddressActivity.class));
                    finish();
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

    private void login(String phone, String password){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        HashMap<String, String> map = new HashMap<>();
//        map.put("mobile", phone);
//        map.put("password", MD5.encodeByMD5(password));
//        VolleyUtil.SendVolleyPostBean(MainApi.LOGIN, map, new VolleyUtil.volleyInterface() {
//            @Override
//            public void ResponseResult(Object jsonObject) {
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                overridePendingTransition(R.anim.my_scale_action, R.anim.my_alpha_action);
//            }
//
//            @Override
//            public void ResponseError(Object volleyError) {
//                ToastUtil.showShortToast(volleyError.toString());
//            }
//        });
    }
}
