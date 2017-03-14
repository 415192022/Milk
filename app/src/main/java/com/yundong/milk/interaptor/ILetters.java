package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LettersBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface ILetters {
    Observable<LettersBean>  getLetters(
            String page,
            String sort
    );
}
