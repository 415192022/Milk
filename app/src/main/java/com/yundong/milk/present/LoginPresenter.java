package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.CheckVerificationCodeImpl;
import com.yundong.milk.interaptor.impl.GetVerificationImpl;
import com.yundong.milk.interaptor.impl.LoginImpl;
import com.yundong.milk.interaptor.impl.RegisterImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CheckVerificationCodeBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.RegistBean;
import com.yundong.milk.view.ICheckVerificationView;
import com.yundong.milk.view.IGetVerificationView;
import com.yundong.milk.view.ILoginView;
import com.yundong.milk.view.IRegisterView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class LoginPresenter {
    private ILoginView iLoginView;
    private LoginImpl login;

    private IGetVerificationView iGetVerificationView;
    private GetVerificationImpl getVerification;

    private ICheckVerificationView iCheckVerificationView;
    private CheckVerificationCodeImpl checkVerificationCode;

    private IRegisterView iRegisterView;
    private RegisterImpl register;

    private static LoginPresenter loginPresenter;

    private LoginPresenter() {
        login = new LoginImpl();
        getVerification = new GetVerificationImpl();
        checkVerificationCode = new CheckVerificationCodeImpl();
        register = new RegisterImpl();
    }

    public static LoginPresenter getInstance() {
        loginPresenter = new LoginPresenter();
        return loginPresenter;
    }

    public LoginPresenter with(ILoginView iLoginView, IGetVerificationView iGetVerificationView, ICheckVerificationView iCheckVerificationView, IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        this.iCheckVerificationView = iCheckVerificationView;
        this.iGetVerificationView = iGetVerificationView;
        this.iLoginView = iLoginView;
        return loginPresenter;
    }
    public LoginPresenter with(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        return loginPresenter;
    }

    public void login(String mobile, String pwd) {
        login.login(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iLoginView.loginOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginBean baseReceiveBean) {
                        iLoginView.login(baseReceiveBean);
                    }
                })
        ;
    }

    //获取验证码
    public void getVerification(String mobile) {
        getVerification.getVerificationCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iGetVerificationView.getVerificationCodeOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iGetVerificationView.getVerificationCode(baseReceiveBean);
                    }
                })
        ;
    }

    public void checkVerification(CheckVerificationCodeBean checkVerificationCodeBean) {
        checkVerificationCode.checkVerificationCode(checkVerificationCodeBean.getMobile(), checkVerificationCodeBean.getMsg())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCheckVerificationView.checkVerificationCodeOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iCheckVerificationView.checkVerificationCode(baseReceiveBean);
                    }
                });
    }

    public void register(String mobile,String pwd) {
        register.register(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegistBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iRegisterView.registerOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegistBean loginBean) {
                        iRegisterView.register(loginBean);
                    }
                });

    }

}
