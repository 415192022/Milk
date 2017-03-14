package com.yundong.milk.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.adapter.home.RecommentTypeAdapter;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.home.activity.InformationActivity;
import com.yundong.milk.home.activity.SearchGoodsActivity;
import com.yundong.milk.home.adapter.HomeGoodsListAdapter;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.GoodsCommentBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.present.HomeFragmentPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IGoodsCommentView;
import com.yundong.milk.view.ILettersView;
import com.yundong.milk.view.IRecommentTypeView;
import com.yundong.milk.widget.NoScrollListView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/12/12.
 * 首页
 */
public class HomeFragment extends BaseFragment
        implements
        View.OnClickListener
        , SwipyRefreshLayout.OnRefreshListener
        , IRecommentTypeView
        , ILettersView
        , IGoodsCommentView {

    private SwipyRefreshLayout mPullToRefreshView;
    private HomeGoodsListAdapter mAdapter;
    private NoScrollListView mListView;

    private HomeFragmentPresenter homeFragmentPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        final SweetAlertDialog dialog = new SweetAlertDialog(getActivity());
//        dialog.showCancelButton(true);
//        dialog.setTitleText(getString(R.string.tips_for_user));
//        dialog.setConfirmText(getString(R.string.confirm));
//        dialog.show();
//        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                dialog.dismiss();
//            }
//        });
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_head_list;
    }



    private TextView tv_article;
    private RecyclerView rv_type_head;
    private RecommentTypeAdapter recommentTypeAdapter;
    @Override
    public void initView(View view) {
        view.findViewById(R.id.imgSearch).setOnClickListener(this);
        tv_article = (TextView) view.findViewById(R.id.tv_article);
        view.findViewById(R.id.lineInformation).setOnClickListener(this);

        mPullToRefreshView = (SwipyRefreshLayout) view.findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnRefreshListener(this);
        mPullToRefreshView.setRefreshing(true);
        mPullToRefreshView.setDirection(SwipyRefreshLayoutDirection.BOTH);

        mListView = (NoScrollListView) view.findViewById(R.id.listView);
        mAdapter = new HomeGoodsListAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        rv_type_head= (RecyclerView) view.findViewById(R.id.rv_type_head);
        rv_type_head.setHasFixedSize(true);
        rv_type_head.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recommentTypeAdapter=new RecommentTypeAdapter(getActivity());
        rv_type_head.setAdapter(recommentTypeAdapter);

        homeFragmentPresenter = HomeFragmentPresenter.getInstance().with(this, this, this);
        homeFragmentPresenter.getRecommentType();
        homeFragmentPresenter.getLetters("1", "desc");
        homeFragmentPresenter.getGoodsComment("1");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSearch://搜索
                startActivity(new Intent(getActivity(), SearchGoodsActivity.class));
                break;
            case R.id.txtRoomMilk://
                // 常温牛奶
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


    //获取推荐分类
    @Override
    public void getRecommentType(final RecommentTypeBean recommentTypeBean) {
        Observable.from(recommentTypeBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        recommentTypeAdapter.getRecommentTypeBeanArrayList().add(recommentTypeBean);
                        recommentTypeAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void getRecommentTypeOnError(String e) {
        ToastUtil.showShortToast("加载推荐分类时出错。");
        ToastUtil.showShortToast(e);
        commpletRefresh();
    }

    @Override
    public void getLetters(LettersBean lettersBean) {
        Observable.from(lettersBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LettersBean.LettersDataO.LettersDataA>() {
                    @Override
                    public void onCompleted() {
                        mPullToRefreshView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LettersBean.LettersDataO.LettersDataA lettersDataA) {
                        tv_article.setText(lettersDataA.getArticle_title());
                    }
                });
    }

    @Override
    public void getLettersOnError(String e) {
        ToastUtil.showShortToast("加载快报出错" + e);
        commpletRefresh();
    }

    private GoodsCommentBean goodsCommentBean;
    //获得推荐商品
    @Override
    public void getGoodsComment(GoodsCommentBean goodsCommentBean) {
        this.goodsCommentBean=goodsCommentBean;
        Observable.from(goodsCommentBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA>() {
                    @Override
                    public void onCompleted() {
                        mPullToRefreshView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA goodsCommentDataA) {
                        //推荐商品Adapter
                        mAdapter.getmList().add(goodsCommentDataA);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void getGoodsCommentOnError(String e) {
        commpletRefresh();
    }


    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            mAdapter.getmList().clear();
            //        homeFragmentPresenter.getRecommentType();
            homeFragmentPresenter.getLetters("1", "desc");
            homeFragmentPresenter.getGoodsComment("1");
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            mPullToRefreshView.setRefreshing(false);
            if(null != goodsCommentBean){
                if(goodsCommentBean.getData().getCurrent_page().equals(goodsCommentBean.getData().getTotal_page())){
                    ToastUtil.showShortToast("没有更多数据了");
                }else{
                    homeFragmentPresenter.getGoodsComment(String.valueOf(Integer.parseInt(goodsCommentBean.getData().getCurrent_page()) + 1));
                }
            }
        }

    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPullToRefreshView.setRefreshing(false);
        }
    }

    MyHandler myHandler = new MyHandler();

    private void commpletRefresh() {
        myHandler.sendEmptyMessage(0);
    }
}
