package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface IMyCollectionDeleteView {
    void deleteMyCollection(BaseReceiveBean baseReceiveBean);
    void deleteMyCollectionOnError(String e);
}
