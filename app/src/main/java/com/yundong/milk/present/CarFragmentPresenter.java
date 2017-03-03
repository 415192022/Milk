package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.CarListImpl;
import com.yundong.milk.interaptor.impl.DeleteCarImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.view.ICarListView;
import com.yundong.milk.view.IDeleteCarView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class CarFragmentPresenter {
    private static CarFragmentPresenter carFragmentPresenter;

    //购物车列表
    private CarListImpl carList;
    private ICarListView iCarListView;

    //删除购物车商品
    private DeleteCarImpl deleteCar;
    private IDeleteCarView iDeleteCarView;

    private CarFragmentPresenter() {
        carList = new CarListImpl();
        deleteCar = new DeleteCarImpl();
    }

    public static CarFragmentPresenter getInstance() {
        carFragmentPresenter = new CarFragmentPresenter();
        return carFragmentPresenter;
    }

    public CarFragmentPresenter with(ICarListView iCarListView, IDeleteCarView iDeleteCarView) {
        this.iCarListView = iCarListView;
        this.iDeleteCarView = iDeleteCarView;
        return carFragmentPresenter;
    }

    public void getCarList(String user_id) {
        carList.getCarList(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCarListView.getCarListOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(CarListBean carListBean) {
                        iCarListView.getCarList(carListBean);
                    }
                });
    }

    public void deleteCar(String cart_id, final int positon){
        deleteCar.deleteCar(cart_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iDeleteCarView.deleteCarOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iDeleteCarView.deleteCar(baseReceiveBean,positon);
                    }
                });
    }

}
