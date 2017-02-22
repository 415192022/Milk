package com.yundong.milk.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.GoodsFirstSortAdapter;
import com.yundong.milk.home.adapter.GoodsSecondSortAdapter;
import com.yundong.milk.home.adapter.GoodsSortListTwoAdapter;
import com.yundong.milk.view.HorizontalListView;
import com.yundong.milk.view.recyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/19.
 * 商品列表
 */
public class GoodsListTwoSortActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private HorizontalListView mFirstSortListView;
    private ListView mSecondSortListView;
    private XRecyclerView mRecyclerViewGoodsList;
    private GoodsSortListTwoAdapter mGoodsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_two_sort);
        initTitle(R.string.commodity, true);
        mFirstSortListView = (HorizontalListView) findViewById(R.id.firstSortListView);
        mSecondSortListView = (ListView) findViewById(R.id.secondSortListView);
        mRecyclerViewGoodsList = (XRecyclerView) findViewById(R.id.recyclerViewGoodsList);
        mRecyclerViewGoodsList.initParams();
        mRecyclerViewGoodsList.setLoadingListener(this);

        // 初始化第一级分类数据
        ArrayList<String> firstSortList = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            firstSortList.add("成人牛奶" + i);
        }
        final GoodsFirstSortAdapter firstSortAdapter = new GoodsFirstSortAdapter(this, firstSortList);
        mFirstSortListView.setAdapter(firstSortAdapter);
        mFirstSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                firstSortAdapter.setSelectIndex(i);
                firstSortAdapter.notifyDataSetChanged();
            }
        });
        // 初始化第二级分类
        ArrayList<String> secondSortList = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            secondSortList.add("全部" + i);
        }
        final GoodsSecondSortAdapter secondSortAdapter = new GoodsSecondSortAdapter(this, secondSortList);
        mSecondSortListView.setAdapter(secondSortAdapter);
        secondSortAdapter.setSelectIndex(0);
        mSecondSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                secondSortAdapter.setSelectIndex(i);
                secondSortAdapter.notifyDataSetChanged();
            }
        });
        // 初始化商品列表界面
        mGoodsListAdapter = new GoodsSortListTwoAdapter(this);
        mRecyclerViewGoodsList.setAdapter(mGoodsListAdapter);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
