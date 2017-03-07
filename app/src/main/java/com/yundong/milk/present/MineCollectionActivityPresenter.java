package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.MyCollectionImpl;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.view.IMyCollectionView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class MineCollectionActivityPresenter {
    private static MineCollectionActivityPresenter mineCollectionActivityPresenter;

    //我的收藏
    private MyCollectionImpl myCollection;
    private IMyCollectionView iMyCollectionView;

    private MineCollectionActivityPresenter() {
        myCollection = new MyCollectionImpl();
    }

    public static MineCollectionActivityPresenter getInstance() {
        mineCollectionActivityPresenter = new MineCollectionActivityPresenter();
        return mineCollectionActivityPresenter;
    }

    public MineCollectionActivityPresenter with(IMyCollectionView iMyCollectionView) {
        this.iMyCollectionView = iMyCollectionView;
        return mineCollectionActivityPresenter;
    }

    public void myCollection(String user_id) {
        myCollection.myCollection(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyCollectionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMyCollectionView.myCollectionOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(MyCollectionBean myCollectionBean) {
                        iMyCollectionView.myCollection(myCollectionBean);
                    }
                });
    }
}
