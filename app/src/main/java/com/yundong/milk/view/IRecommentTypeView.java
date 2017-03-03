package com.yundong.milk.view;

import com.yundong.milk.model.RecommentTypeBean;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IRecommentTypeView {
    void getRecommentType(RecommentTypeBean recommentTypeBean);
    void getRecommentTypeOnError(String e);
}
