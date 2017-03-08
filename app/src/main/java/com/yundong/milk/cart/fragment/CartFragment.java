package com.yundong.milk.cart.fragment;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.cart.adapter.CartFooterListAdapter;
import com.yundong.milk.cart.adapter.CartListAdapter;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.present.CarFragmentPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.ICarListView;
import com.yundong.milk.view.IDeleteCarView;
import com.yundong.milk.widget.LoadingDialog;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/12/12.
 * 购物车
 */
public class CartFragment extends BaseFragment
        implements
        SwipyRefreshLayout.OnRefreshListener,
        ICarListView,
        IDeleteCarView
{
    private SwipyRefreshLayout srl_car_list;

    private XRecyclerView mRecyclerView;
    private CartListAdapter mAdapter;
    private XRecyclerView mFooterRecyclerView;
    private CartFooterListAdapter mFooterAdapter;

    private CarFragmentPresenter carFragmentPresenter;

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
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        View footerView = View.inflate(getActivity(), R.layout.cart_footer, null);
        mFooterRecyclerView = (XRecyclerView) footerView.findViewById(R.id.recyclerViewFooter);
        mFooterRecyclerView.initParams();
        mFooterRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mFooterAdapter = new CartFooterListAdapter(getActivity());
        mFooterRecyclerView.setAdapter(mFooterAdapter);
        mRecyclerView.addFootView(footerView);
//        mFooterRecyclerView.setPullRefreshEnabled(false);
//        mFooterRecyclerView.setLoadingMoreEnabled(false);
        srl_car_list= (SwipyRefreshLayout) view.findViewById(R.id.srl_car_list);
        srl_car_list.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary));
        srl_car_list.setOnRefreshListener(this);
        srl_car_list.setRefreshing(true);
        carFragmentPresenter=CarFragmentPresenter.getInstance().with(this,this);
        carFragmentPresenter.getCarList(YunDongApplication.getLoginBean().getData().getUserinfo().getId());

        mAdapter = new CartListAdapter(getActivity(),carFragmentPresenter);
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


    //获得购物车列表
    @Override
    public void getCarList(CarListBean carListBean) {
        final ArrayList<CarListBean.CarListDataA> mList=new ArrayList<>();
        Observable.from(carListBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarListBean.CarListDataA>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.addData(mList);
                        srl_car_list.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CarListBean.CarListDataA carListDataA) {
                        mList.add(carListDataA);
                    }
                });
    }

    @Override
    public void getCarListOnError(String e) {

    }

    @Override
    public void deleteCar(BaseReceiveBean baseReceiveBean,int position) {
        mAdapter.deleteDataByIndex(position);
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void deleteCarOnError(String e) {

    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            mAdapter.getmList().clear();
            carFragmentPresenter.getCarList(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            ToastUtil.showShortToast("上拉加载");
            srl_car_list.setRefreshing(false);
        }

    }
}