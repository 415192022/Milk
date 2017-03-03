package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.model.LoginBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ILettersView {
    void getLetters(LettersBean baseReceiveBean);
    void getLettersOnError(String e);
}
