package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.MyCollectionDeleteImpl;
import com.yundong.milk.interaptor.impl.MyCollectionImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.view.IMyCollectionDeleteView;
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

    //删除收藏
    private MyCollectionDeleteImpl myCollectionDelete;
    private IMyCollectionDeleteView iMyCollectionDeleteView;

    private MineCollectionActivityPresenter() {
        myCollection = new MyCollectionImpl();
        myCollectionDelete = new MyCollectionDeleteImpl();
    }

    public static MineCollectionActivityPresenter getInstance() {
        mineCollectionActivityPresenter = new MineCollectionActivityPresenter();
        return mineCollectionActivityPresenter;
    }

    public MineCollectionActivityPresenter with(IMyCollectionView iMyCollectionView, IMyCollectionDeleteView iMyCollectionDeleteView) {
        this.iMyCollectionView = iMyCollectionView;
        this.iMyCollectionDeleteView = iMyCollectionDeleteView;
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

    public void deleteMyCollection(String coll_id) {
        myCollectionDelete.deleteMyCollection(coll_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMyCollectionDeleteView.deleteMyCollectionOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iMyCollectionDeleteView.deleteMyCollection(baseReceiveBean);
                    }
                });
    }
}
