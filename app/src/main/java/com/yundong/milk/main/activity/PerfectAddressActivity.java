package com.yundong.milk.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.yundong.milk.util.ToastUtil;

/**
 * Created by lj on 2016/12/23.
 * 完善地址信息
 */
public class PerfectAddressActivity extends BaseActivity implements OnGetGeoCoderResultListener {

    private TextView mTxtCurrentLocation;
    private LocationClient mLocationClient;
    private BDLocationListener mBDLocationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_address);
        findViewById(R.id.txtLocate).setOnClickListener(this);
        findViewById(R.id.txtComplete).setOnClickListener(this);
        mTxtCurrentLocation = (TextView) findViewById(R.id.txtCurrentLocation);
        // 声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
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
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.txtLocate:
                mBDLocationListener = new MyBDLocationListener();
                getLocation();
                break;
        }
    }

    /** 获得所在位置经纬度及详细地址 */
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
}
