package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.dialog.SweetAlertDialog;

/**
 * Created by lj on 2016/12/28.
 * 收货地址
 */
public class ReceiptAddressActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initTitle(R.string.receipt_address, true);
        findViewById(R.id.txtEdit).setOnClickListener(this);
        ((TextView) findViewById(R.id.txtName)).setText("李华");
        ((TextView) findViewById(R.id.txtPhone)).setText("15683424932");
        ((TextView) findViewById(R.id.txtAddress)).setText("上海上海市宝山区逸仙路华滋奔腾大厦A座2206");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtEdit:
                final SweetAlertDialog dialog = new SweetAlertDialog(this);
                dialog.showCancelButton(true);
               dialog.setTitleText(getString(R.string.whether_apply_for_edit_address));
                dialog.setCancelText(getString(R.string.cancel));
                dialog.setConfirmText(getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ToastUtil.showShortToast(R.string.request_send_success);
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
}
