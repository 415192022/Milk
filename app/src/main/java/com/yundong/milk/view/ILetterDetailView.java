package com.yundong.milk.view;

import com.yundong.milk.model.LetterDetailBean;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public interface ILetterDetailView {
    void letterDetail(LetterDetailBean letterDetailBean);
    void letterDetailOnError(String e);
}
