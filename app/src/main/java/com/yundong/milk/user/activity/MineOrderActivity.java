package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.adapter.OrderFragmentAdapter;
import com.yundong.milk.user.fragment.MineOrderFragment;
import com.yundong.milk.view.IOrderListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/11/14.
 * 我的订单
 */
public class MineOrderActivity extends BaseActivity {

    private RadioGroup mRadGroup;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_order);
        initTitle(R.string.mine_order, true);
        mRadGroup = (RadioGroup) findViewById(R.id.radGroup);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> list = new ArrayList<>();
        list.add(MineOrderFragment.newInstance(0));
        list.add(MineOrderFragment.newInstance(1));
        list.add(MineOrderFragment.newInstance(2));
        list.add(MineOrderFragment.newInstance(3));
        mRadGroup.setOnCheckedChangeListener(new ViewGroupListener());
        OrderFragmentAdapter adapter = new OrderFragmentAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new PagerChangedListener());
        initTitleColor();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageRight:
                break;
        }
    }

    private void initTitleColor(){
        switch (getIntent().getIntExtra("position", -1)){
            case 0:
                mRadGroup.check(R.id.radAll);
                break;
            case 1:
                mRadGroup.check(R.id.radWaitPay);
                break;
            case 2:
                mRadGroup.check(R.id.radWaitReceipt);
                break;
            case 3:
                mRadGroup.check(R.id.radWaitComment);
                break;
        }
    }


    class PagerChangedListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    mRadGroup.check(R.id.radAll);
                    break;
                case 1:
                    mRadGroup.check(R.id.radWaitPay);
                    break;
                case 2:
                    mRadGroup.check(R.id.radWaitReceipt);
                    break;
                case 3:
                    mRadGroup.check(R.id.radWaitComment);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class ViewGroupListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int isChecked) {
            if (isChecked == R.id.radAll){
                mViewPager.setCurrentItem(0, true);
            }else if (isChecked == R.id.radWaitPay){
                mViewPager.setCurrentItem(1, true);
            }else if (isChecked == R.id.radWaitReceipt){
                mViewPager.setCurrentItem(2, true);
            }else if (isChecked == R.id.radWaitComment){
                mViewPager.setCurrentItem(3, true);
            }
        }
    }

}
