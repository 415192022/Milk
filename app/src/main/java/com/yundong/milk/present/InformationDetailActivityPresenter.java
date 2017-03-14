package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.LetterDetailImpl;
import com.yundong.milk.interaptor.impl.LettersImpl;
import com.yundong.milk.model.LetterDetailBean;
import com.yundong.milk.view.ILetterDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public class InformationDetailActivityPresenter {
    private static InformationDetailActivityPresenter informationDetailActivityPresenter;

    private LetterDetailImpl letterDetail;
    private ILetterDetailView iLetterDetailView;

    private InformationDetailActivityPresenter() {
        letterDetail = new LetterDetailImpl();
    }

    public static InformationDetailActivityPresenter getInstance() {
        informationDetailActivityPresenter = new InformationDetailActivityPresenter();
        return informationDetailActivityPresenter;
    }

    public InformationDetailActivityPresenter with(ILetterDetailView iLetterDetailView) {
        this.iLetterDetailView = iLetterDetailView;
        return informationDetailActivityPresenter;
    }

    public void getLetterDetail(String article_id) {
        letterDetail.letterDetail(article_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LetterDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iLetterDetailView.letterDetailOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(LetterDetailBean letterDetailBean) {
                        iLetterDetailView.letterDetail(letterDetailBean);
                    }
                });
    }


}
