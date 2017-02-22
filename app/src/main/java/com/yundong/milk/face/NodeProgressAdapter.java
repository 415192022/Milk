package com.yundong.milk.face;


import com.yundong.milk.user.model.LogisticsData;

import java.util.List;

/**
 * Created by lj on 2017/1/4.
 */
public interface NodeProgressAdapter {

    /**
     * 返回集合大小
     *
     * @return
     */
    int getCount();

    /**
     * 适配数据的集合
     *
     * @return
     */
    List<LogisticsData> getData();

}
