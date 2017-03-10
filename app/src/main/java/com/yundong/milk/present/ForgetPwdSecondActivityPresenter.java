package com.yundong.milk.present;

import com.yundong.milk.interaptor.IFindPwd;
import com.yundong.milk.interaptor.impl.FindPwdImpl;
import com.yundong.milk.model.FindPwdBean;
import com.yundong.milk.view.IFindPwdView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class ForgetPwdSecondActivityPresenter {
    private static ForgetPwdSecondActivityPresenter forgetPwdSecondActivityPresenter;

    //找回密码
    private FindPwdImpl findPwd;
    private IFindPwdView iFindPwdView;

    private ForgetPwdSecondActivityPresenter() {
        findPwd = new FindPwdImpl();
    }

    public static ForgetPwdSecondActivityPresenter getInstance() {
        forgetPwdSecondActivityPresenter = new ForgetPwdSecondActivityPresenter();
        return forgetPwdSecondActivityPresenter;
    }

    public ForgetPwdSecondActivityPresenter with(IFindPwdView iFindPwdView) {
        this.iFindPwdView = iFindPwdView;
        return forgetPwdSecondActivityPresenter;
    }

    public void findPwd(String mobile,
                        String password,
                        String msg) {
        findPwd.findPwd(mobile,password,msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FindPwdBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iFindPwdView.findPwdOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(FindPwdBean findPwdBean) {
                        iFindPwdView.findPwd(findPwdBean);
                    }
                });
    }
}
