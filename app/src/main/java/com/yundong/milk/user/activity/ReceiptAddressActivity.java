package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.present.ConfirmOrderActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IApplyForModifyView;
import com.yundong.milk.view.IReceiveGoodsAddressView;
import com.yundong.milk.widget.dialog.SweetAlertDialog;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

/**
 * Created by lj on 2016/12/28.
 * 收货地址
 */
public class ReceiptAddressActivity extends BaseActivity implements IReceiveGoodsAddressView,IApplyForModifyView, SwipeRefreshLayout.OnRefreshListener {
    private ConfirmOrderActivityPresenter confirmOrderActivityPresenter;

    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAddress;
    private SwipeRefreshLayout srl_receive_goods_address;

    private TextView tv_txtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initTitle(R.string.receipt_address, true);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        srl_receive_goods_address = (SwipeRefreshLayout) findViewById(R.id.srl_receive_goods_address);
        tv_txtEdit = (TextView) findViewById(R.id.tv_txtEdit);
        tv_txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SweetAlertDialog dialog = new SweetAlertDialog(ReceiptAddressActivity.this);
                dialog.showCancelButton(true);
                dialog.setTitleText(getString(R.string.whether_apply_for_edit_address));
                dialog.setCancelText(getString(R.string.cancel));
                dialog.setConfirmText(getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if(null != receiveGoodsAddressBean){
                            ToastUtil.showShortToast(receiveGoodsAddressBean.getData().getAddress_id());
                            //申请修改收货地址
                            if (null != receiveGoodsAddressBean) {
                                confirmOrderActivityPresenter.applyFroModify(receiveGoodsAddressBean.getData().getAddress_id());
                            }

                        }else {
                            ToastUtil.showShortToast("提交失败,请关闭页面后重试！");
                        }
                        dialog.dismiss();
                    }
                });

            }
        });

//        srl_receive_goods_address.setDirection(SwipyRefreshLayoutDirection.TOP);
        srl_receive_goods_address.setRefreshing(true);
        srl_receive_goods_address.setOnRefreshListener(this);

        confirmOrderActivityPresenter = ConfirmOrderActivityPresenter.getInstance().with(this,this);
        confirmOrderActivityPresenter.receiveGoodsAddress(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
    }


    private ReceiveGoodsAddressBean receiveGoodsAddressBean;

    @Override
    public void receiveGoodsAddress(ReceiveGoodsAddressBean receiveGoodsAddressBean) {
        this.receiveGoodsAddressBean = receiveGoodsAddressBean;
        if (receiveGoodsAddressBean.getData() == null || receiveGoodsAddressBean.getData().equals(null)) {
            ToastUtil.showShortToast("收货地址为空");
        } else {
            txtName.setText(receiveGoodsAddressBean.getData().getUname());
            txtPhone.setText(receiveGoodsAddressBean.getData().getPhone());
            txtAddress.setText(receiveGoodsAddressBean.getData().getArea_info());
            srl_receive_goods_address.setRefreshing(false);
        }

    }

    @Override
    public void receiveGoodsAddressOnError(String e) {
        ToastUtil.showShortToast("获取收货地址失败");
        srl_receive_goods_address.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        confirmOrderActivityPresenter.receiveGoodsAddress(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
    }

    //申请修改收货地址
    @Override
    public void applyForModify(BaseReceiveBean baseReceiveBean) {
        if(baseReceiveBean.getCode().equals("3000")){
        }
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void applyForModifyOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
