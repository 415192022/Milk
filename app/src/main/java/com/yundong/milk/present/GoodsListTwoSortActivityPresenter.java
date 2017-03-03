package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.AllTypeImpl;
import com.yundong.milk.interaptor.impl.RecommentTypeImpl;
import com.yundong.milk.interaptor.impl.TypeBrandImpl;
import com.yundong.milk.interaptor.impl.TypeGoodsImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.model.TypeBrandBean;
import com.yundong.milk.model.TypeGoodsBean;
import com.yundong.milk.view.IAllTypeView;
import com.yundong.milk.view.IRecommentTypeView;
import com.yundong.milk.view.ITypeBrandView;
import com.yundong.milk.view.ITypeGoodsView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class GoodsListTwoSortActivityPresenter {
    private static GoodsListTwoSortActivityPresenter homeFragmentPresenter;

    //获得所有分类
    private IAllTypeView iAllTypeView;
    private AllTypeImpl allType;

    //获得分类品牌
    private ITypeBrandView iTypeBrandView;
    private TypeBrandImpl typeBrand;

    //获得分类商品
    private ITypeGoodsView iTypeGoodsView;
    private TypeGoodsImpl typeGoods;

    private GoodsListTwoSortActivityPresenter() {
        allType = new AllTypeImpl();
        typeBrand = new TypeBrandImpl();
        typeGoods=new TypeGoodsImpl();
    }

    public GoodsListTwoSortActivityPresenter with(IAllTypeView iAllTypeView, ITypeBrandView iTypeBrandView,ITypeGoodsView iTypeGoodsView) {
        this.iAllTypeView = iAllTypeView;
        this.iTypeBrandView = iTypeBrandView;
        this.iTypeGoodsView=iTypeGoodsView;
        return homeFragmentPresenter;
    }

    public static GoodsListTwoSortActivityPresenter getInstance() {
        homeFragmentPresenter = new GoodsListTwoSortActivityPresenter();
        return homeFragmentPresenter;
    }

    //获得所有分类
    public void getAllType() {
        allType.getAllType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iAllTypeView.getAllTypeOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        iAllTypeView.getAllType(recommentTypeBean);
                    }
                });
    }
    //获得分类品牌
    public void getTypeBrand(String class_id){
        typeBrand.getTypeBrandBean(class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TypeBrandBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iTypeBrandView.getTypeBrandOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(TypeBrandBean baseReceivesBean) {
                        iTypeBrandView.getTypeBrand(baseReceivesBean);
                    }
                });
    }

    //获得分类商品
    public void getTypeGoods(String class_id, String brand_id, String page, String page_data){
        typeGoods.getTypeGoods(class_id,brand_id,page,page_data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TypeGoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iTypeGoodsView.getTypeGoodsOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(TypeGoodsBean typeGoodsBean) {
                        iTypeGoodsView.getTypeGoods(typeGoodsBean);
                    }
                });
    }
}
