package com.yundong.milk.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.model.RegistBean;
import com.yundong.milk.model.RegisterCompleteLoginBean;
import com.yundong.milk.present.LoginPresenter;
import com.yundong.milk.present.PerfectAddressActivityPresenter;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IAddReceiveAddressView;
import com.yundong.milk.view.ILoginView;
import com.yundong.milk.widget.popupwindow.PCAPopupWindow;

/**
 * Created by lj on 2016/12/23.
 * 完善地址信息
 */
public class PerfectAddressActivity extends BaseActivity
        implements
        OnGetGeoCoderResultListener,
        PCAPopupWindow.OnCompleteListenner,
        IAddReceiveAddressView ,
        ILoginView
{

    private TextView mTxtCurrentLocation;
    private LocationClient mLocationClient;
    private BDLocationListener mBDLocationListener;


    private RelativeLayout rl_location;

    private PerfectAddressActivityPresenter perfectAddressActivityPresenter;


    private RegisterCompleteLoginBean registerCompleteLoginBean;

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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveUserInfo(RegisterCompleteLoginBean registerCompleteLoginBean) {
        this.registerCompleteLoginBean = registerCompleteLoginBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_address);
        findViewById(R.id.txtLocate).setOnClickListener(this);
        findViewById(R.id.txtComplete).setOnClickListener(this);
        mTxtCurrentLocation = (TextView) findViewById(R.id.txtCurrentLocation);
        rl_location = (RelativeLayout) findViewById(R.id.rl_location);
        rl_location.setOnClickListener(this);
        // 声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());

        perfectAddressActivityPresenter = PerfectAddressActivityPresenter.getInstance().with(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtComplete:
                String consignee = ((EditText) findViewById(R.id.editConsignee)).getText().toString().trim();
                String phone = ((EditText) findViewById(R.id.editPhone)).getText().toString().trim();
                String currentLocation = ((TextView) findViewById(R.id.txtCurrentLocation)).getText().toString().trim();
                String detailAddress = ((EditText) findViewById(R.id.editDetailAddress)).getText().toString().trim();
                if (TextUtils.isEmpty(consignee)) {
                    ToastUtil.showShortToast(R.string.please_input_consignee);
                } else if (TextUtils.isEmpty(phone)) {
                    ToastUtil.showShortToast(R.string.please_input_phone_number);
                } else if (TextUtils.isEmpty(currentLocation)) {
                    ToastUtil.showShortToast(R.string.please_locate_current_location);
                } else if (TextUtils.isEmpty(detailAddress)) {
                    ToastUtil.showShortToast(R.string.please_input_detail_address);
                } else {
//                    startActivity(new Intent(this, MainActivity.class));
//                    finish();
                    //添加收货地址
                    ToastUtil.showShortToast(province + "  " + city + "  " + area);
                    perfectAddressActivityPresenter.addReceiveAddress(
                            registerCompleteLoginBean.getRegistBean().getData().getId(),
                            registerCompleteLoginBean.getRegistBean().getData().getPhone(),
                            registerCompleteLoginBean.getRegistBean().getData().getUname(),
                            province.getArea_name(),
                            province.getArea_id(),
                            city.getArea_name(),
                            city.getArea_id(),
                            area.getArea_name(),
                            area.getArea_id(),
                            detailAddress

                    );
                }
                break;
            case R.id.txtLocate:
                mBDLocationListener = new MyBDLocationListener();
                getLocation();
                break;
            case R.id.rl_location:
                new PCAPopupWindow(this, this).showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    /**
     * 获得所在位置经纬度及详细地址
     */
    public void getLocation() {
        // 声明定位参数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式 高精度
        option.setCoorType("bd09ll");// 设置返回定位结果是百度经纬度 默认gcj02
        option.setScanSpan(5000);// 设置发起定位请求的时间间隔 单位ms
        option.setIsNeedAddress(true);// 设置定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 设置定位结果包含手机机头 的方向
        // 设置定位参数
        mLocationClient.setLocOption(option);
        // 启动定位
        mLocationClient.start();
        // 注册监听
        mLocationClient.registerLocationListener(mBDLocationListener);
    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    //填完收货地址后登陆结果
    @Override
    public void login(LoginBean baseReceiveBean) {
        if (baseReceiveBean.getCode().equals("2000")) {
        } else if (baseReceiveBean.getCode().equals("3004")) {
            ToastUtil.showShortToast("密码错误");
        }
    }

    @Override
    public void loginOnError(String e) {

    }


    private class MyBDLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            // 非空判断
            if (location != null) {
                // 根据BDLocation 对象获得经纬度以及详细地址信息
                if (mLocationClient.isStarted()) {
                    // 获得位置之后停止定位
                    mLocationClient.stop();
                }
                mTxtCurrentLocation.setText(location.getProvince() + " : " + location.getCity());
            }
        }

    }

    PCABean.PCAData province;
    PCABean.PCAData city;
    PCABean.PCAData area;

    //选择好省市区
    @Override
    public void OnComplete(PCABean.PCAData province, PCABean.PCAData city, PCABean.PCAData area) {
        this.province = province;
        this.city = city;
        this.area = area;
        ((TextView) findViewById(R.id.txtCurrentLocation)).setText(province.getArea_name() + " " + city.getArea_name() + " " + area.getArea_name());
    }

    //添加收货地址
    @Override
    public void addReceiveAddress(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
        LoginPresenter.getInstance()
                .with(this)
                .login(registerCompleteLoginBean.getRegistBean().getData().getPhone(), registerCompleteLoginBean.getPwd());
    }

    @Override
    public void addReceiveAddressOnError(String e) {
        ToastUtil.showShortToast(e);
    }


}

