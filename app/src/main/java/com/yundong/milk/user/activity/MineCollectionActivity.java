package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.user.adapter.MineCollectionListAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.dialog.SweetAlertDialog;
import com.yundong.milk.widget.recyclerview.ItemSidesHelper;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import java.util.HashMap;

/**
 * Created by lj on 2016/12/28.
 * 我的收藏
 */
public class MineCollectionActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    private TextView mTxtEdit;
    private XRecyclerView mRecyclerView;
    private MineCollectionListAdapter mAdapter;
    private HashMap<Integer, Boolean> mItemStatus = new HashMap<>();
    private int mClickStatus = 0;//0未点击  1点击
    private boolean mIsClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.mine_collection, true, getString(R.string.edit), this);
        mTxtEdit = (TextView) findViewById(R.id.txtRight);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MineCollectionListAdapter(this, mTxtEdit);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnItemClickListener(new ItemSidesHelper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mClickStatus == 0) {
                    startActivity(new Intent(MineCollectionActivity.this, GoodsDetailActivity.class));
                }
//                if (mAdapter.getMap().get(position - 1) == true) {
//                    ToastUtil.showShortToast(mAdapter.getCheckListSize() + " ** ");
//                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtRight: //编辑
                if (!mIsClick) {
                    mClickStatus = 1;
                    mTxtEdit.setText(R.string.complete);
                    mAdapter.setShowBox();
                    mAdapter.notifyDataSetChanged();
                } else {
                    mClickStatus = 0;
                    int size = mAdapter.getCheckListSize();

                    if (size > 0) {
//                        if (mTxtEdit.getText().equals("删除")) {
                        mTxtEdit.setText(R.string.delete);
                        final SweetAlertDialog dialog = new SweetAlertDialog(this);
                        dialog.showCancelButton(true);
                        dialog.setTitleText(getString(R.string.whether_delete) + "  " + mAdapter.getCheckListSize());
                        dialog.setCancelText(getString(R.string.cancel));
                        dialog.setConfirmText(getString(R.string.confirm));
                        dialog.show();
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                ToastUtil.showShortToast("我要删除  " + mAdapter.getCheckListSize());
                                dialog.dismiss();
//                                    mAdapter.initMap();
                                mTxtEdit.setText(R.string.edit);
                                mAdapter.mIsShowBox = false;
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismiss();
                                mTxtEdit.setText(R.string.edit);
                                mAdapter.mIsShowBox = false;
                                mAdapter.notifyDataSetChanged();
                            }
                        });
//                        }
                    } else {
                        mTxtEdit.setText(R.string.edit);
                        mAdapter.mIsShowBox = false;
                        mAdapter.notifyDataSetChanged();
                    }
                    mAdapter.initMap();
                    mAdapter.removeCheckList();
                }


//                if (mAdapter.getCheckListSize() == 0) {
//                    mTxtEdit.setText(R.string.complete);
//                }else if (mAdapter.getCheckListSize() > 0){
//                    mTxtEdit.setText(R.string.delete);
//                }
                mIsClick = !mIsClick;
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
