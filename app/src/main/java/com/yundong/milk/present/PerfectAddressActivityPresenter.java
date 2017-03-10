package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.AddReceiveAddressImpl;
import com.yundong.milk.interaptor.impl.PCAImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.view.IAddReceiveAddressView;
import com.yundong.milk.view.IPCAView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class PerfectAddressActivityPresenter {
    private static PerfectAddressActivityPresenter perfectAddressActivityPresenter;

    //获得省市区
    private PCAImpl pca;
    private IPCAView ipcaView;

    //添加收货地址
    private AddReceiveAddressImpl addReceiveAddress;
    private IAddReceiveAddressView iAddReceiveAddressView;

    private PerfectAddressActivityPresenter() {
        pca = new PCAImpl();
        addReceiveAddress = new AddReceiveAddressImpl();
    }

    public static PerfectAddressActivityPresenter getInstance() {
        perfectAddressActivityPresenter = new PerfectAddressActivityPresenter();
        return perfectAddressActivityPresenter;
    }

    public PerfectAddressActivityPresenter with(IPCAView ipcaView) {
        this.ipcaView = ipcaView;
        return perfectAddressActivityPresenter;
    }

    public PerfectAddressActivityPresenter with(IAddReceiveAddressView iAddReceiveAddressView) {
        this.iAddReceiveAddressView = iAddReceiveAddressView;
        return perfectAddressActivityPresenter;
    }

    public void getP(String area_parent_id) {
        pca.getPCA(area_parent_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ipcaView.getPOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(PCABean pcaBean) {
                        ipcaView.getP(pcaBean);
                    }
                });
        ;
    }

    public void getC(String area_parent_id) {
        pca.getPCA(area_parent_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ipcaView.getCOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(PCABean pcaBean) {
                        ipcaView.getC(pcaBean);
                    }
                });
        ;
    }

    public void getA(String area_parent_id) {
        pca.getPCA(area_parent_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ipcaView.getAOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(PCABean pcaBean) {
                        ipcaView.getA(pcaBean);
                    }
                });
    }


    //添加收货地址
    public void addReceiveAddress(String user_id,
                                  String phone,
                                  String uname,
                                  String province_name,
                                  String province_id,
                                  String city_name,
                                  String city_id,
                                  String area_name,
                                  String area_id,
                                  String area_info) {
        addReceiveAddress.addReceiveAddress(
                user_id,
                phone,
                uname,
                province_name,
                province_id,
                city_name,
                city_id,
                area_name,
                area_id,
                area_info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iAddReceiveAddressView.addReceiveAddressOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iAddReceiveAddressView.addReceiveAddress(baseReceiveBean);
                    }
                });

    }
}
