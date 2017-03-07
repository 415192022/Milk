package com.yundong.milk.widget.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class RecyclerViewLoadMore extends RecyclerView {
    public RecyclerViewLoadMore(Context context) {
        super(context);
    }

    public RecyclerViewLoadMore(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewLoadMore(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private float xDistance, yDistance, xLast, yLast;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }

    private OnScrollViewScrollListener mOnScrollViewScrollListener = null;
    public void setOnScrollListener(OnScrollViewScrollListener listener) {
        this.mOnScrollViewScrollListener = listener;
    }

    public interface OnScrollViewScrollListener {
        public void onScrollChanged(int x, int y, int oldX, int oldY);
    }
}
