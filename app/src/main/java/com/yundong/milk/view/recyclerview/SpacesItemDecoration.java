package com.yundong.milk.view.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ww on 2016/8/23.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 6;
        outRect.right = 6;
        outRect.bottom = 20;
        outRect.top = 0;
    }

}
