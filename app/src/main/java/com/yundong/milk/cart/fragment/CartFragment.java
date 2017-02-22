package com.yundong.milk.cart.fragment;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.cart.adapter.CartFooterListAdapter;
import com.yundong.milk.cart.adapter.CartListAdapter;
import com.yundong.milk.view.LoadingDialog;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2016/12/12.
 * 购物车
 */
public class CartFragment extends BaseFragment implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;
    private CartListAdapter mAdapter;
    private XRecyclerView mFooterRecyclerView;
    private CartFooterListAdapter mFooterAdapter;

    private LoadingDialog dialog;
    private Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    };

    @Override
    public int getRootView() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        View footerView = View.inflate(getActivity(), R.layout.cart_footer, null);
        mFooterRecyclerView = (XRecyclerView) footerView.findViewById(R.id.recyclerViewFooter);
        mFooterRecyclerView.initParams();
        mFooterRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mFooterAdapter = new CartFooterListAdapter(getActivity());
        mFooterRecyclerView.setAdapter(mFooterAdapter);
        mRecyclerView.addFootView(footerView);
        mAdapter = new CartListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

//        dialog = new LoadingDialog(getActivity(), R.layout.dialog_loading);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mHandler.sendEmptyMessage(1);
//            }
//        }).start();

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}