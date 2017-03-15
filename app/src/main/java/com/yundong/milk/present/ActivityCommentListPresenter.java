package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.GoodsCommentListImpl;
import com.yundong.milk.model.GoodsCommentListBean;
import com.yundong.milk.view.IGoodsCommentListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class ActivityCommentListPresenter {
    private static ActivityCommentListPresenter activityCommentListPresenter;

    //获得评论列表
    private GoodsCommentListImpl goodsCommentList;
    private IGoodsCommentListView iGoodsCommentListView;


    private ActivityCommentListPresenter() {
        goodsCommentList = new GoodsCommentListImpl();
    }

    public static ActivityCommentListPresenter getInstance() {
        activityCommentListPresenter = new ActivityCommentListPresenter();
        return activityCommentListPresenter;
    }

    public ActivityCommentListPresenter with(IGoodsCommentListView iGoodsCommentListView) {
        this.iGoodsCommentListView = iGoodsCommentListView;
        return activityCommentListPresenter;
    }

    public void goodsCommentList(String goods_id, String page, String scores) {
        goodsCommentList.goodsCommentList(goods_id, page, scores).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                iGoodsCommentListView.goodsCommentList(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                iGoodsCommentListView.goodsCommentListOnError(t.getMessage());
            }
        });
    }

}
