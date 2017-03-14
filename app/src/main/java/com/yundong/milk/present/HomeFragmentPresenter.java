package com.yundong.milk.present;

import android.widget.Toast;

import com.yundong.milk.interaptor.impl.GoodsCommentImpl;
import com.yundong.milk.interaptor.impl.LettersImpl;
import com.yundong.milk.interaptor.impl.RecommentTypeImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.GoodsCommentBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IGoodsCommentView;
import com.yundong.milk.view.ILettersView;
import com.yundong.milk.view.IRecommentTypeView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class HomeFragmentPresenter {
    private static HomeFragmentPresenter homeFragmentPresenter;
    //获得推荐分类
    private RecommentTypeImpl recommentType;
    private IRecommentTypeView iRecommentTypeView;

    //获得快报
    private LettersImpl letters;
    private ILettersView lettersView;

    //获得推荐商品
    private GoodsCommentImpl goodsComment;
    private IGoodsCommentView iGoodsCommentView;

    private HomeFragmentPresenter() {
        recommentType = new RecommentTypeImpl();
        letters = new LettersImpl();
        goodsComment=new GoodsCommentImpl();
    }

    public HomeFragmentPresenter with(IRecommentTypeView iRecommentTypeView) {
        this.iRecommentTypeView = iRecommentTypeView;
        return homeFragmentPresenter;
    }
    public HomeFragmentPresenter with(ILettersView iLettersView) {
        this.lettersView = iLettersView;
        return homeFragmentPresenter;
    }
    public HomeFragmentPresenter with(IRecommentTypeView iRecommentTypeView, ILettersView lettersView,IGoodsCommentView iGoodsCommentView) {
        this.iRecommentTypeView = iRecommentTypeView;
        this.lettersView = lettersView;
        this.iGoodsCommentView=iGoodsCommentView;
        return homeFragmentPresenter;
    }

    public static HomeFragmentPresenter getInstance() {
        homeFragmentPresenter = new HomeFragmentPresenter();
        return homeFragmentPresenter;
    }

    public void getRecommentType() {
        recommentType.getRecommentType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iRecommentTypeView.getRecommentTypeOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        iRecommentTypeView.getRecommentType(recommentTypeBean);
                    }
                });
    }

    public void getLetters(String page, String sort) {
        letters.getLetters(page,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LettersBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        lettersView.getLettersOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(LettersBean baseReceiveBean) {
                        lettersView.getLetters(baseReceiveBean);
                    }
                })
                ;
    }

    public void getGoodsComment(String page){
        goodsComment.getGoodsComment(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iGoodsCommentView.getGoodsCommentOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsCommentBean goodsCommentBean) {
                        iGoodsCommentView.getGoodsComment(goodsCommentBean);
                    }
                });
    }
}
