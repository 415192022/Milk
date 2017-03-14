package com.yundong.milk.commodity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.adapter.home.GoodsClassAdpter;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.commodity.adapter.CommodityListAdapter;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.present.HomeFragmentPresenter;
import com.yundong.milk.view.IRecommentTypeView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/12/12.
 * 商品
 */
public class CommodityFragment extends BaseFragment implements IRecommentTypeView {

    private XRecyclerView mRecyclerView;
    private GoodsClassAdpter mAdapter;

    private HomeFragmentPresenter homeFragmentPresenter;

    @Override
    public int getRootView() {
        return R.layout.fragment_commodity;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mAdapter = new GoodsClassAdpter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        homeFragmentPresenter=HomeFragmentPresenter.getInstance().with(this);
        homeFragmentPresenter.getRecommentType();
    }

    @Override
    public void getRecommentType(RecommentTypeBean recommentTypeBean) {
        Observable.from(recommentTypeBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        mAdapter.getmList().add(recommentTypeBean);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getRecommentTypeOnError(String e) {

    }
}