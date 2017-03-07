package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.ModifyNickNameImpl;
import com.yundong.milk.interaptor.impl.UploadHeadImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.view.IModifyNickNameView;
import com.yundong.milk.view.IUploadHeadView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class SetingActivityPresenter {
    private static SetingActivityPresenter setingActivityPresenter;

    //上传头像
    private UploadHeadImpl uploadHead;
    private IUploadHeadView iUploadHeadView;

    //修改昵称
    private ModifyNickNameImpl modifyNickName;
    private IModifyNickNameView iModifyNickNameView;

    public SetingActivityPresenter with(IUploadHeadView iUploadHeadView, IModifyNickNameView iModifyNickNameView) {
        this.iUploadHeadView = iUploadHeadView;
        this.iModifyNickNameView = iModifyNickNameView;
        return setingActivityPresenter;
    }

    private SetingActivityPresenter() {
        uploadHead = new UploadHeadImpl();
        modifyNickName = new ModifyNickNameImpl();
    }

    public static SetingActivityPresenter getInstance() {
        setingActivityPresenter = new SetingActivityPresenter();
        return setingActivityPresenter;
    }

    //上传头像
    public void upLoadHead(String img, String user_id) {
        uploadHead.upLoadHead(img, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iUploadHeadView.upLoadHeadOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iUploadHeadView.upLoadHead(baseReceiveBean);
                    }
                });
    }

    //修改昵称
    public void modifyNickName(String name, String user_id) {
        modifyNickName.modifyNacikName(name, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iModifyNickNameView.modifyNickNameOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iModifyNickNameView.modifyNickName(baseReceiveBean);
                    }
                });
    }
}
