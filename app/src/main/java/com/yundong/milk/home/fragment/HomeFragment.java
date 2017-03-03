package com.yundong.milk.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.home.activity.InformationActivity;
import com.yundong.milk.home.activity.SearchGoodsActivity;
import com.yundong.milk.home.adapter.HomeGoodsListAdapter;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsCommentBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.present.HomeFragmentPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IGoodsCommentView;
import com.yundong.milk.view.ILettersView;
import com.yundong.milk.view.IRecommentTypeView;
import com.yundong.milk.widget.NoScrollListView;
import com.yundong.milk.widget.PullToRefreshView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        , PullToRefreshView.OnHeaderRefreshListener
        , PullToRefreshView.OnFooterRefreshListener
        , IRecommentTypeView
        , ILettersView
        , IGoodsCommentView {

    private PullToRefreshView mPullToRefreshView;
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

    private TextView txtRoomMilk;
    private TextView txtLowMilk;
    private TextView txtChildMilk;
    private TextView txtOldMilk;
    private TextView txtAdultMilk;
    private TextView txtMineralWater;
    private TextView txtNewProduct;
    private TextView txtActivity;
    private List<TextView> recommentTypeViews = new ArrayList<>();


    private TextView tv_article;

    @Override
    public void initView(View view) {
        view.findViewById(R.id.imgSearch).setOnClickListener(this);
        txtRoomMilk = (TextView) view.findViewById(R.id.txtRoomMilk);
        txtRoomMilk.setOnClickListener(this);
        txtLowMilk = (TextView) view.findViewById(R.id.txtLowMilk);
        txtLowMilk.setOnClickListener(this);
        txtChildMilk = (TextView) view.findViewById(R.id.txtChildMilk);
        txtChildMilk.setOnClickListener(this);
        txtOldMilk = (TextView) view.findViewById(R.id.txtOldMilk);
        txtOldMilk.setOnClickListener(this);
        txtAdultMilk = (TextView) view.findViewById(R.id.txtAdultMilk);
        txtAdultMilk.setOnClickListener(this);
        txtMineralWater = (TextView) view.findViewById(R.id.txtMineralWater);
        txtMineralWater.setOnClickListener(this);
        txtNewProduct = (TextView) view.findViewById(R.id.txtNewProduct);
        txtNewProduct.setOnClickListener(this);
        txtActivity = (TextView) view.findViewById(R.id.txtActivity);
        txtActivity.setOnClickListener(this);
        tv_article = (TextView) view.findViewById(R.id.tv_article);
        recommentTypeViews.add(txtRoomMilk);
        recommentTypeViews.add(txtLowMilk);
        recommentTypeViews.add(txtChildMilk);
        recommentTypeViews.add(txtOldMilk);
        recommentTypeViews.add(txtAdultMilk);
        recommentTypeViews.add(txtMineralWater);
        recommentTypeViews.add(txtNewProduct);
        recommentTypeViews.add(txtActivity);
        view.findViewById(R.id.lineInformation).setOnClickListener(this);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.main_pull_refresh_view);
        mListView = (NoScrollListView) view.findViewById(R.id.listView);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        mPullToRefreshView.setLastUpdated(new Date().toLocaleString());


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

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
//        homeFragmentPresenter.getRecommentType();
//        homeFragmentPresenter.getLetters("1", "desc");
//        homeFragmentPresenter.getGoodsComment("1");
        commpletRefresh();
    }

    private List<String> recommentTypes = new ArrayList<>();

    @Override
    public void getRecommentType(final RecommentTypeBean recommentTypeBean) {
        Observable.from(recommentTypeBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onCompleted() {
                        for (int i = 0; i < recommentTypes.size(); i++) {
                            recommentTypeViews.get(i).setText(recommentTypes.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        recommentTypes.add(recommentTypeBean.getGc_name());
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
                        mPullToRefreshView.onHeaderRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LettersBean.LettersDataO.LettersDataA lettersDataA) {
                        tv_article.setText(lettersDataA.getArticle_title());
                    }
                });
        commpletRefresh();
    }

    @Override
    public void getLettersOnError(String e) {
        ToastUtil.showShortToast("加载快报出错" + e);
        commpletRefresh();
    }


    //获得推荐商品
    @Override
    public void getGoodsComment(GoodsCommentBean goodsCommentBean) {
        Observable.from(goodsCommentBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA>() {
                    @Override
                    public void onCompleted() {
                        mPullToRefreshView.onHeaderRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA goodsCommentDataA) {
                        //推荐商品Adapter
                        mAdapter = new HomeGoodsListAdapter(getActivity());
                        mAdapter.addData(goodsCommentDataA);
                        mListView.setAdapter(mAdapter);
                    }
                });
        commpletRefresh();
    }


    @Override
    public void getGoodsCommentOnError(String e) {
        commpletRefresh();
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPullToRefreshView.onHeaderRefreshComplete();
        }
    }
    MyHandler myHandler = new MyHandler();
    private void commpletRefresh() {
        myHandler.sendEmptyMessage(0);
    }
}
