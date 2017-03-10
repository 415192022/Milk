package com.yundong.milk.view;

import com.yundong.milk.model.PCABean;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public interface IPCAView {
    void getP(PCABean pcaBean);
    void getPOnError(String e);
    void getC(PCABean pcaBean);
    void getCOnError(String e);
    void getA(PCABean pcaBean);
    void getAOnError(String e);
}
