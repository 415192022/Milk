package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeGoodsBean;

import retrofit2.Call;
import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ITypeGoods {
    Observable<TypeGoodsBean> getTypeGoods(String class_id, String brand_id, String page, String page_data);
}
