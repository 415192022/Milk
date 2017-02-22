package com.yundong.milk.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.HotSearchAdapter;

/**
 * Created by lj on 2017/1/5.
 * 搜索
 */
public class SearchGoodsActivity extends BaseActivity{

    private GridView mGridView;
    private HotSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.txtSearch).setOnClickListener(this);
        mGridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new HotSearchAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(SearchGoodsActivity.this, GoodsListTwoSortActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtSearch://搜索
                startActivity(new Intent(this, GoodsListTwoSortActivity.class));
                break;
        }
    }
}
