package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

/**
 * Created by lj on 2017/1/4.
 * 申请退款
 */
public class ApplyForMoney extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_money);
        initTitle(R.string.apply_for_refund, true);
        findViewById(R.id.txtReturnApply).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtReturnApply:
                final SweetAlertDialog dialog = new SweetAlertDialog(this);
                dialog.showCancelButton(true);
                dialog.setTitleText(getString(R.string.whether_return));
                dialog.setCancelText(getString(R.string.cancel));
                dialog.setConfirmText(getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                        finish();
                    }
                });
                break;
        }
    }
}
