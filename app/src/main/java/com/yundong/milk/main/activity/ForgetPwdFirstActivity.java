package com.yundong.milk.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;
import com.yundong.milk.util.ToastUtil;

/**
 * Created by lj on 2016/12/23.
 * 忘记密码之界面一
 */
public class ForgetPwdFirstActivity extends BaseActivity{

    private TextView mTxtGetVerCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd_first);
        initTitle(R.string.forgetPwd, true);
        mTxtGetVerCode = (TextView) findViewById(R.id.txtGetVerCode);
        mTxtGetVerCode.setOnClickListener(this);
        findViewById(R.id.btnNext).setOnClickListener(this);
        if (!CacheActivity.activityList.contains(ForgetPwdFirstActivity.this)) {
            CacheActivity.addActivity(ForgetPwdFirstActivity.this);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtGetVerCode:
                getVerCode();
                break;
            case R.id.btnNext:
                goNext();
                break;
        }
    }

    private void getVerCode(){
        String account = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
        if (TextUtils.isEmpty(account)){
            ToastUtil.showShortToast(R.string.please_input_account);
        }else if (account.length() != 11){
            ToastUtil.showShortToast(R.string.phone_number_not_correct);
        }else {
            timer.start();
        }
    }

    private void goNext(){
        String account = ((EditText) findViewById(R.id.editAccount)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.editPassword)).getText().toString().trim();
        if (TextUtils.isEmpty(account)){
            ToastUtil.showShortToast(R.string.please_input_account);
        }else if (account.length() != 11){
            ToastUtil.showShortToast(R.string.phone_number_not_correct);
        }else if (TextUtils.isEmpty(password)){
            ToastUtil.showShortToast(R.string.please_input_SMS_verification_code);
        }else {
            startActivity(new Intent(this, ForgetPwdSecondActivity.class));
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
        if(null != timer) {
            timer.cancel();
        }
    }
}
