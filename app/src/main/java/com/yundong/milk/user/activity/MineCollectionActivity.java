package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.present.MineCollectionActivityPresenter;
import com.yundong.milk.user.adapter.MineCollectionListAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IMyCollectionDeleteView;
import com.yundong.milk.view.IMyCollectionView;
import com.yundong.milk.widget.dialog.SweetAlertDialog;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/12/28.
 * 我的收藏
 */
public class MineCollectionActivity extends BaseActivity
        implements
        IMyCollectionView
        , SwipyRefreshLayout.OnRefreshListener
        , IMyCollectionDeleteView {

    private TextView mTxtEdit;
    private RecyclerView rv_mycollection;
    private SwipyRefreshLayout srl_refund;
    private MineCollectionListAdapter mAdapter;
    private int mClickStatus = 0;//0未点击  1点击
    private boolean mIsClick = false;
    private MineCollectionActivityPresenter mineCollectionActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.mine_collection, true, getString(R.string.edit), this);


        srl_refund = (SwipyRefreshLayout) findViewById(R.id.srl_message_center);
        srl_refund.setOnRefreshListener(this);
        srl_refund.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl_refund.setRefreshing(true);


        mTxtEdit = (TextView) findViewById(R.id.txtRight);
        rv_mycollection = (RecyclerView) findViewById(R.id.rv_mycollection);
        rv_mycollection.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MineCollectionListAdapter(this, mTxtEdit);
        rv_mycollection.setAdapter(mAdapter);

        mineCollectionActivityPresenter = MineCollectionActivityPresenter.getInstance().with(this, this);
        mineCollectionActivityPresenter.myCollection(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
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
                                String collIds = "";
                                for (int i = 0; i < mAdapter.getCheckedList().size(); i++) {
                                    if(mAdapter.getCheckedList().size()>1){
                                        collIds += "," + mAdapter.getMyCollectionBeanDataArrays().get(i).getId();
                                    }else{
                                        collIds +=  mAdapter.getMyCollectionBeanDataArrays().get(i).getId();
                                    }

                                }
                                ToastUtil.showShortToast("我要删除  " + collIds);
                                mineCollectionActivityPresenter.deleteMyCollection(collIds);
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
    public void myCollection(MyCollectionBean myCollectionBean) {
        Observable.from(myCollectionBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyCollectionBean.MyCollectionBeanData.MyCollectionBeanDataArray>() {
                    @Override
                    public void onCompleted() {
                        srl_refund.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyCollectionBean.MyCollectionBeanData.MyCollectionBeanDataArray myCollectionBeanDataArray) {
                        mAdapter.getMyCollectionBeanDataArrays().add(myCollectionBeanDataArray);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void myCollectionOnError(String e) {

    }


    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            mAdapter.getMyCollectionBeanDataArrays().clear();
            mAdapter.notifyDataSetChanged();
            mineCollectionActivityPresenter.myCollection(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            ToastUtil.showShortToast("上拉加载");
            srl_refund.setRefreshing(false);
        }

    }

    @Override
    public void deleteMyCollection(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
        mAdapter.getMyCollectionBeanDataArrays().clear();
        mAdapter.notifyDataSetChanged();
        mineCollectionActivityPresenter.myCollection(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
    }

    @Override
    public void deleteMyCollectionOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
