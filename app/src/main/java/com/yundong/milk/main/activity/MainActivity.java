package com.yundong.milk.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cart.fragment.CartFragment;
import com.yundong.milk.commodity.fragment.CommodityFragment;
import com.yundong.milk.home.fragment.HomeFragment;
import com.yundong.milk.user.fragment.UserFragment;

public class MainActivity extends BaseActivity {

    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private final Class fragmentArray[] = {HomeFragment.class, CommodityFragment.class, CartFragment.class, UserFragment.class};
    private int mTitleArray[] = {R.string.home, R.string.commodity, R.string.cart, R.string.userCenter};
    private int mImageViewArray[] = {R.drawable.tab_home, R.drawable.tab_commodity, R.drawable.tab_cart, R.drawable.tab_user};
    private String mTagArray[] = {"home", "commodity", "cart", "user"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; ++i) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTagArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(null);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.home_tab, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mImageViewArray[index]);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTitleArray[index]);
        return view;
    }
}
