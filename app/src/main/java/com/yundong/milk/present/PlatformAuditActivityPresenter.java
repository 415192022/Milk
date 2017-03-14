package com.yundong.milk.present;

import com.yundong.milk.interaptor.ICommitPlateformAudit;
import com.yundong.milk.interaptor.impl.ApplyForCompanyImpl;
import com.yundong.milk.interaptor.impl.CommitPlateformAuditImpl;
import com.yundong.milk.interaptor.impl.PlateformAuditImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PlatformAuditBean;
import com.yundong.milk.view.IApplyForChangeCompanyView;
import com.yundong.milk.view.ICommitPlateformAuditView;
import com.yundong.milk.view.IPlateformAuditView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class PlatformAuditActivityPresenter {
    private static PlatformAuditActivityPresenter platformAuditActivityPresenter;

    //获得审核信息
    private PlateformAuditImpl plateformAudit;
    private IPlateformAuditView iPlateformAuditView;

    //提交审核信息
    private CommitPlateformAuditImpl commitPlateformAudit;
    private ICommitPlateformAuditView iCommitPlateformAuditView;

    //申请修改公司
    private ApplyForCompanyImpl applyForCompany;
    private IApplyForChangeCompanyView iApplyForChangeCompanyView;


    private PlatformAuditActivityPresenter() {
        plateformAudit = new PlateformAuditImpl();
        commitPlateformAudit = new CommitPlateformAuditImpl();
        applyForCompany=new ApplyForCompanyImpl();
    }

    public static PlatformAuditActivityPresenter getinstance() {
        platformAuditActivityPresenter = new PlatformAuditActivityPresenter();
        return platformAuditActivityPresenter;
    }

    public PlatformAuditActivityPresenter with(
            IPlateformAuditView iPlateformAuditView
            , ICommitPlateformAuditView iCommitPlateformAuditView
            ,IApplyForChangeCompanyView iApplyForChangeCompanyView
    ) {
        this.iCommitPlateformAuditView = iCommitPlateformAuditView;
        this.iPlateformAuditView = iPlateformAuditView;
        this.iApplyForChangeCompanyView = iApplyForChangeCompanyView;
        return platformAuditActivityPresenter;
    }

    public void platformAudit(String user_id) {
        plateformAudit.platformAudit(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlatformAuditBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iPlateformAuditView.plateformAuditOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(PlatformAuditBean platformAuditBean) {
                        iPlateformAuditView.plateformAudit(platformAuditBean);
                    }
                });
    }

    public void commitPlateformAudit(
            String user_id
            , String company_name
            , String charge_people
            , String charge_phone
            , String license
            , String province_name
            , String province_id
            , String city_name
            , String city_id
            , String area_name
            , String area_id
            , String area_info) {
        commitPlateformAudit.commitPlateformAudit(user_id, company_name, charge_people, charge_phone, license, province_name, province_id, city_name, city_id, area_name, area_id, area_info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCommitPlateformAuditView.commitPlateformAuditOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iCommitPlateformAuditView.commitPlateformAudit(baseReceiveBean);
                    }
                })
        ;
    }

    public void applyForCompany(String company_id){
        applyForCompany.applyForChangeCommany(company_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iApplyForChangeCompanyView.applyForChangeCompanyOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iApplyForChangeCompanyView.applyForChangeCompany(baseReceiveBean);
                    }
                });
    }
}
