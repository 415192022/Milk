package com.yundong.milk.home.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.home.fragment.GoodsListFragment;

import java.util.ArrayList;


/**
 * Created by lj on 2016/11/7.
 */
public class GoodsListFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<String> mGoodsSortList;
    public static GoodsListFragment pageFragment;

    public GoodsListFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<String> list) {
        super(fm);
        this.mContext = context;
        this.mGoodsSortList = list;
    }

    public static GoodsListFragment getCurrent(){
        return pageFragment;
    }

    @Override
    public GoodsListFragment getItem(int position) {
        pageFragment = GoodsListFragment.newInstance(position);
        return GoodsListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mGoodsSortList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(mGoodsSortList.get(position));
        return view;
    }
}
