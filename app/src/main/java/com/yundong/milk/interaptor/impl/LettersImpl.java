package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ILetterDetailService;
import com.yundong.milk.api.service.ILettersService;
import com.yundong.milk.interaptor.ILetterDetail;
import com.yundong.milk.interaptor.ILetters;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LetterDetailBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.util.RetrofitUtils;

import retrofit2.http.Field;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class LettersImpl implements ILetters {


    @Override
    public Observable<LettersBean> getLetters(String page, String sort) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, ILettersService.class)
                .getLetters(page, sort)
                ;
    }
}
