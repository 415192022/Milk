package com.yundong.milk.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.GoodsListFragmentPagerAdapter;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/5.
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private GoodsListFragmentPagerAdapter mPagerAdapter;
    private ArrayList<String> mGoodsSortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        findViewById(R.id.imgBack).setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mGoodsSortList = new ArrayList<>();

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < 20; i++){
            mGoodsSortList.add("伊利牛奶" + i);
        }

        mPagerAdapter = new GoodsListFragmentPagerAdapter(getSupportFragmentManager(), this, mGoodsSortList);
        mViewPager.setAdapter(mPagerAdapter);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mPagerAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        ToastUtil.showShortToast(tab.getPosition()+"====");
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
