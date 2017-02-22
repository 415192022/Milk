package com.yundong.milk.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.home.activity.InformationActivity;
import com.yundong.milk.home.activity.SearchGoodsActivity;
import com.yundong.milk.home.adapter.HomeGoodsListAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.NoScrollListView;
import com.yundong.milk.view.PullToRefreshView;
import com.yundong.milk.view.dialog.SweetAlertDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lj on 2016/12/12.
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {

    private PullToRefreshView mPullToRefreshView;
    private HomeGoodsListAdapter mAdapter;
    private NoScrollListView mListView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final SweetAlertDialog dialog = new SweetAlertDialog(getActivity());
        dialog.showCancelButton(true);
        dialog.setTitleText(getString(R.string.tips_for_user));
        dialog.setConfirmText(getString(R.string.confirm));
        dialog.show();
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_head_list;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.imgSearch).setOnClickListener(this);
        view.findViewById(R.id.txtRoomMilk).setOnClickListener(this);
        view.findViewById(R.id.txtLowMilk).setOnClickListener(this);
        view.findViewById(R.id.txtChildMilk).setOnClickListener(this);
        view.findViewById(R.id.txtOldMilk).setOnClickListener(this);
        view.findViewById(R.id.txtAdultMilk).setOnClickListener(this);
        view.findViewById(R.id.txtMineralWater).setOnClickListener(this);
        view.findViewById(R.id.txtNewProduct).setOnClickListener(this);
        view.findViewById(R.id.txtActivity).setOnClickListener(this);
        view.findViewById(R.id.lineInformation).setOnClickListener(this);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.main_pull_refresh_view);
        mListView = (NoScrollListView) view.findViewById(R.id.listView);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        mPullToRefreshView.setLastUpdated(new Date().toLocaleString());
        mAdapter = new HomeGoodsListAdapter(getActivity());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgSearch://搜索
                startActivity(new Intent(getActivity(), SearchGoodsActivity.class));
                break;
            case R.id.txtRoomMilk://常温牛奶
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtLowMilk://低温牛奶
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtChildMilk://儿童牛奶
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtOldMilk://老年牛奶
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtAdultMilk://成人牛奶
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtMineralWater://矿泉水
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtNewProduct://新品
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.txtActivity://活动
                startActivity(new Intent(getActivity(), GoodsListTwoSortActivity.class));
                break;
            case R.id.lineInformation://新闻资讯
                startActivity(new Intent(getActivity(), InformationActivity.class));
                break;
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete("更新于:" + Calendar.getInstance().getTime().toLocaleString());
                mPullToRefreshView.onHeaderRefreshComplete();
                ToastUtil.showShortToast("数据刷新完成!");
            }
        }, 3000);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mPullToRefreshView.onFooterRefreshComplete();
//                gridViewData.add(R.drawable.pic1);
                ToastUtil.showShortToast("加载更多数据!");
            }
        }, 3000);
    }
}
