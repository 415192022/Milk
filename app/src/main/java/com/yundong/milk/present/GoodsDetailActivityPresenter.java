package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.AddCarImpl;
import com.yundong.milk.interaptor.impl.GoodsClassCommentImpl;
import com.yundong.milk.interaptor.impl.GoodsCollectionImpl;
import com.yundong.milk.interaptor.impl.GoodsDetailsImpl;
import com.yundong.milk.interaptor.impl.GoodsSubtractImpl;
import com.yundong.milk.interaptor.impl.IsCollectionGoodsImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsClassCommentBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.view.IAddCarView;
import com.yundong.milk.view.IGoodsClassCommentView;
import com.yundong.milk.view.IGoodsCollectonView;
import com.yundong.milk.view.IGoodsDetailsView;
import com.yundong.milk.view.IGoodsSubtractView;
import com.yundong.milk.view.IIsCollectionGoodsView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public class GoodsDetailActivityPresenter {
    private static GoodsDetailActivityPresenter goodsDetailActivityPresenter;

    //商品详情
    private GoodsDetailsImpl goodsDetails;
    private IGoodsDetailsView iGoodsDetailsView;

    //收藏
    private GoodsCollectionImpl goodsCollection;
    private IGoodsCollectonView iGoodsCollectonView;

    //是否收藏
    private IsCollectionGoodsImpl isCollectionGoods;
    private IIsCollectionGoodsView iIsCollectionGoodsView;

    //添加购物车
    private AddCarImpl addCar;
    private IAddCarView iAddCarView;

    //相似商品推荐
    private GoodsClassCommentImpl goodsClassComment;
    private IGoodsClassCommentView iGoodsClassCommentView;


    private GoodsDetailActivityPresenter() {
        goodsDetails = new GoodsDetailsImpl();
        goodsCollection = new GoodsCollectionImpl();
        isCollectionGoods = new IsCollectionGoodsImpl();
        addCar = new AddCarImpl();
        goodsClassComment = new GoodsClassCommentImpl();
    }

    public GoodsDetailActivityPresenter with(IGoodsDetailsView iGoodsDetailsView
            , IGoodsCollectonView iGoodsCollectonView
            , IIsCollectionGoodsView iIsCollectionGoodsView
            , IAddCarView iAddCarView
            , IGoodsClassCommentView iGoodsClassCommentView
    ) {
        this.iGoodsDetailsView = iGoodsDetailsView;
        this.iGoodsCollectonView = iGoodsCollectonView;
        this.iIsCollectionGoodsView = iIsCollectionGoodsView;
        this.iAddCarView = iAddCarView;
        this.iGoodsClassCommentView = iGoodsClassCommentView;
        return goodsDetailActivityPresenter;
    }

    public static GoodsDetailActivityPresenter getInstance() {
        goodsDetailActivityPresenter = new GoodsDetailActivityPresenter();
        return goodsDetailActivityPresenter;
    }

    public void getGoodsDetails(String goods_id) {
        goodsDetails.getTypeGoods(goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsDetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iGoodsDetailsView.getGoodsDetailsOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsDetailsBean goodsDetailsBean) {
                        iGoodsDetailsView.getGoodsDetails(goodsDetailsBean);
                    }
                });
    }

    public void goodsCollection(String user_id, String goods_id) {
        goodsCollection.goodsCollection(user_id, goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iGoodsCollectonView.collectionOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iGoodsCollectonView.collection(baseReceiveBean);
                    }
                });
    }

    public void isCollectionGoods(String user_id, String goods_id) {
        isCollectionGoods.isCollectionGoods(user_id, goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iIsCollectionGoodsView.isCollectionGoodsOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iIsCollectionGoodsView.isCollectionGoods(baseReceiveBean);
                    }
                });
    }

    public void addCar(String user_id, String goods_id, String number) {
        addCar.addCar(user_id, goods_id, number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iAddCarView.addCarOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iAddCarView.addCar(baseReceiveBean);
                    }
                });
    }

    //获得相似商品推荐
    public void getGoodsClassComment(String goods_type, String page_data, String page) {
        goodsClassComment.getGoodsClassComment(goods_type, page_data, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsClassCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iGoodsClassCommentView.getGoodsClassCommentOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsClassCommentBean goodsClassCommentBean) {
                        iGoodsClassCommentView.getGoodsClassComment(goodsClassCommentBean);
                    }
                });
    }

}
