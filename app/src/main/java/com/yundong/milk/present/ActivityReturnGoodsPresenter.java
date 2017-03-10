package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.ReturnGoodsImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.view.IReturnGoodsView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public class ActivityReturnGoodsPresenter {
    private static ActivityReturnGoodsPresenter activityReturnGoodsPresenter;

    private ReturnGoodsImpl returnGoods;
    private IReturnGoodsView iReturnGoodsView;

    private ActivityReturnGoodsPresenter() {
        returnGoods = new ReturnGoodsImpl();
    }

    public static ActivityReturnGoodsPresenter getInstance() {
        activityReturnGoodsPresenter = new ActivityReturnGoodsPresenter();
        return activityReturnGoodsPresenter;
    }

    public ActivityReturnGoodsPresenter with(IReturnGoodsView iReturnGoodsView) {
        this.iReturnGoodsView = iReturnGoodsView;
        return activityReturnGoodsPresenter;
    }

    public void returnGoods(String user_id
            , String order_id
            , String return_reason
            , String return_img) {
        returnGoods.returnGoods(user_id,order_id,return_reason,return_img)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iReturnGoodsView.returnGoodsOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iReturnGoodsView.returnGoods(baseReceiveBean);
                    }
                });

    }
}
