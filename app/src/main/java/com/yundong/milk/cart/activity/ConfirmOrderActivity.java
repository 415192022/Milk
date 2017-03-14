package com.yundong.milk.cart.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.model.GoodsAndAddressBean;
import com.yundong.milk.model.GoodsAndCountBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.present.ConfirmOrderActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IReceiveGoodsAddressView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by lj on 2017/1/5.
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity
        implements
        IReceiveGoodsAddressView {
    private ImageView imgGoodsPic;
    private TextView txtGoodsName;
    private TextView txtShopPrice;
    private TextView txtMarketPrice;
    private TextView tv_address;
    private TextView tv_phone;
    private TextView tv_receiver;
    private TextView tv_totle_price;
    private EditText et_msg;
    private ConfirmOrderActivityPresenter confirmOrderActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initTitle(R.string.confirmDetail, true);
        ((TextView) findViewById(R.id.txtMarketPrice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        findViewById(R.id.txtBuyIm).setOnClickListener(this);
        imgGoodsPic = (ImageView) findViewById(R.id.imgGoodsPic);
        txtGoodsName = (TextView) findViewById(R.id.txtGoodsName);
        txtShopPrice = (TextView) findViewById(R.id.txtShopPrice);
        txtMarketPrice = (TextView) findViewById(R.id.txtMarketPrice);
        tv_totle_price = (TextView) findViewById(R.id.tv_totle_price);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_receiver = (TextView) findViewById(R.id.tv_receiver);
        et_msg = (EditText) findViewById(R.id.et_msg);
        confirmOrderActivityPresenter = ConfirmOrderActivityPresenter.getInstance().with(this);
        confirmOrderActivityPresenter.receiveGoodsAddress(YunDongApplication.getLoginBean().getData().getUserinfo().getId());

    }

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

    private GoodsDetailsBean goodsDetailsBean = null;

    private String goodsCount = "";
    private String totlePrice = "";

    //接受货物信息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveGoodsInfo(GoodsAndCountBean goodsAndCountBean) {
        this.goodsDetailsBean = goodsAndCountBean.getGoodsDetailsBean();
        Glide.with(this).load(goodsAndCountBean.getGoodsDetailsBean().getData().getGoods_main_image()).into(imgGoodsPic);
        txtGoodsName.setText(goodsAndCountBean.getGoodsDetailsBean().getData().getGoods_name());
        txtShopPrice.setText(goodsAndCountBean.getGoodsDetailsBean().getData().getGoods_price());
        txtMarketPrice.setText(goodsAndCountBean.getGoodsDetailsBean().getData().getGoods_marketprice());
        tv_totle_price.setText("¥ " + goodsAndCountBean.getTotlePrice());
        ((TextView)findViewById(R.id.txtTotal)).setText("¥ " + totlePrice);
        goodsCount = goodsAndCountBean.getCount();
        totlePrice = goodsAndCountBean.getTotlePrice();
    }

    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray;

    //接受订单货物信息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveGoodsInfo(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
        this.orderListDataArray = orderListDataArray;
        Glide.with(this).load(orderListDataArray.getGoods_main_image()).into(imgGoodsPic);
        txtGoodsName.setText(orderListDataArray.getGoods_name());
        txtShopPrice.setText(orderListDataArray.getGoods_price());
        txtMarketPrice.setText(orderListDataArray.getGoods_marketprice());
        totlePrice = String.valueOf(Float.parseFloat(orderListDataArray.getGoods_price()) * Float.parseFloat(orderListDataArray.getGoods_sum()));
        tv_totle_price.setText("¥ " + totlePrice);
        ((TextView)findViewById(R.id.txtTotal)).setText("¥ " + totlePrice);
        goodsCount = orderListDataArray.getGoods_sum();
    }

    private CarListBean.CarListDataA carListDataA;

    //接受购物车货物信息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveGoodsInfo(CarListBean.CarListDataA carListDataA) {
        this.carListDataA = carListDataA;
        Glide.with(this).load(carListDataA.getGoods_main_image()).into(imgGoodsPic);
        txtGoodsName.setText(carListDataA.getGoods_name());
        txtShopPrice.setText(carListDataA.getGoods_price());
        txtMarketPrice.setText(carListDataA.getGoods_marketprice());
        totlePrice = String.valueOf(Float.parseFloat(carListDataA.getNumber()) * Float.parseFloat(carListDataA.getGoods_price()));
        tv_totle_price.setText("¥ " + totlePrice);
        ((TextView)findViewById(R.id.txtTotal)).setText("¥ " + totlePrice);
        goodsCount = carListDataA.getNumber();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtBuyIm:
                if (null != goodsDetailsBean) {
                    GoodsAndAddressBean goodsAndAddressBean = new GoodsAndAddressBean();
                    goodsAndAddressBean.setGoodsDetailsBean(goodsDetailsBean);
                    goodsAndAddressBean.setReceiveGoodsAddressBean(receiveGoodsAddressBean);
                    goodsAndAddressBean.setMsg(et_msg.getText() + "");
                    goodsAndAddressBean.setCount(goodsCount);
                    goodsAndAddressBean.setTotlePrice(totlePrice);
                    RxBus.getDefault().post(goodsAndAddressBean);

                } else if (null != carListDataA) {
                    GoodsAndAddressBean goodsAndAddressBean = new GoodsAndAddressBean();
                    goodsAndAddressBean.setCarListDataA(carListDataA);
                    goodsAndAddressBean.setReceiveGoodsAddressBean(receiveGoodsAddressBean);
                    goodsAndAddressBean.setMsg(et_msg.getText() + "");
                    goodsAndAddressBean.setCount(goodsCount);
                    goodsAndAddressBean.setTotlePrice(totlePrice);
                    RxBus.getDefault().post(goodsAndAddressBean);
                } else if (null != orderListDataArray) {
                    GoodsAndAddressBean goodsAndAddressBean = new GoodsAndAddressBean();
                    goodsAndAddressBean.setOrderListDataArray(orderListDataArray);
                    goodsAndAddressBean.setReceiveGoodsAddressBean(receiveGoodsAddressBean);
                    goodsAndAddressBean.setMsg(et_msg.getText() + "");
                    goodsAndAddressBean.setCount(goodsCount);
                    goodsAndAddressBean.setTotlePrice(totlePrice);
                    RxBus.getDefault().post(goodsAndAddressBean);

                }
                startActivity(new Intent(this, PaymentActivity.class));
                finish();
                break;
        }
    }

    private ReceiveGoodsAddressBean receiveGoodsAddressBean;

    @Override
    public void receiveGoodsAddress(ReceiveGoodsAddressBean receiveGoodsAddressBean) {
        this.receiveGoodsAddressBean = receiveGoodsAddressBean;
        tv_address.setText(receiveGoodsAddressBean.getData().getProvince_name()
                + " " + receiveGoodsAddressBean.getData().getCity_name()
                + " " + receiveGoodsAddressBean.getData().getArea_info());
        tv_phone.setText(receiveGoodsAddressBean.getData().getPhone());
        tv_receiver.setText(receiveGoodsAddressBean.getData().getUname());
    }

    @Override
    public void receiveGoodsAddressOnError(String e) {

    }

}
