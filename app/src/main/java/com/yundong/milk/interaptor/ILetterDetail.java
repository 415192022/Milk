package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LetterDetailBean;
import com.yundong.milk.model.LettersBean;

import retrofit2.http.Field;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface ILetterDetail {
    Observable<LetterDetailBean> letterDetail(String article_id);
}
