package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.util.ToastUtil;

/**
 * Created by lj on 2016/12/30.
 * 修改昵称
 */
public class MineNickNameActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        initTitle(R.string.mineNickName, true, getString(R.string.save), this);
        findViewById(R.id.imgCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        String userName = ((EditText) findViewById(R.id.editNickName)).getText().toString().trim();
        switch (view.getId()){
            case R.id.txtRight://保存
                if (TextUtils.isEmpty(userName)){
                    ToastUtil.showShortToast(R.string.please_input_userName);
                }else {
                    Intent intent=new Intent(MineNickNameActivity.this,SettingActivity.class);
                    intent.putExtra("NICK_NAME",userName);
                    setResult(100,intent);
                    finish();
                }
                break;
            case R.id.imgCancel: //取消
                if (!TextUtils.isEmpty(userName)){
                    ((EditText) findViewById(R.id.editNickName)).setText(R.string.empty);
                    Intent intent=new Intent(MineNickNameActivity.this,SettingActivity.class);
                    intent.putExtra("NICK_NAME",userName);
                    setResult(100,intent);
                    finish();
                }
                break;
        }
    }
}
