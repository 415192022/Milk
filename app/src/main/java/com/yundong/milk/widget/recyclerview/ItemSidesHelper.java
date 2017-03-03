package com.yundong.milk.widget.recyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by ww on 16-06-06.
 * 帮助显示左滑菜单
 */
public class ItemSidesHelper implements RecyclerView.OnItemTouchListener, GestureDetector.OnGestureListener {

    private final int DEFAULT_DURATION = 200;
    private View mTargetView;

    private int mTouchSlop;
    private int mMaxVelocity;
    private int mMinVelocity;
    private int mLastX;
    private int mLastY;

    private boolean mIsDragging;

    private Animator mExpandAndCollapseAnim;

    private GestureDetectorCompat mGestureDetector;

    private Callback mCallback;

    private OnItemClickListener mOnItemClick;
    private OnItemMenuClickListener mOnItemMenuClick;
    private XRecyclerView mRecyclerView;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemMenuClickListener {
        void onItemMenuClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClick = onItemClickListener;
    }

    public void setOnItemMenuClickListener(OnItemMenuClickListener onItemMenuClickListener) {
        mOnItemMenuClick = onItemMenuClickListener;
    }

    public ItemSidesHelper(Context context, Callback callback, XRecyclerView recyclerView) {
        this.mCallback = callback;
        this.mRecyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(context, this);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMaxVelocity = configuration.getScaledMaximumFlingVelocity();
        mMinVelocity = configuration.getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        int action = MotionEventCompat.getActionMasked(e);
        int x = (int) e.getX();
        int y = (int) e.getY();
        if (rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
            if (mTargetView != null) {
                smoothHorizontalExpandOrCollapse(DEFAULT_DURATION / 2);
                mTargetView = null;
            }
            return false;
        }

        //如果正在运行动画 ，直接拦截什么都不做
        if (mExpandAndCollapseAnim != null && mExpandAndCollapseAnim.isRunning()) {
            return true;
        }

        boolean needIntercept = false;
             switch (action) {
                case MotionEvent.ACTION_DOWN:
                mLastX = (int) e.getX();
                mLastY = (int) e.getY();
                // 如果之前有一个已经打开的项目，当此次点击事件没有发生在右侧的菜单中则返回TRUE，
                // 如果点击的是右侧菜单那么返回FALSE这样做的原因是因为菜单需要响应Onclick
                if (mTargetView != null) {
                    return !inView(x, y);
                }
                mTargetView = mCallback.findTargetView(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (x - mLastX);
                int deltaY = (y - mLastY);
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    return false;
                }
                needIntercept = mIsDragging = mTargetView != null && Math.abs(deltaX) >= mTouchSlop;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //走这是因为没有发生过拦截事件
                if (isExpanded()) {
                    if (inView(x, y)) {
                        // 如果走这那行这个ACTION_UP的事件会发生在右侧的菜单中
                        if(mOnItemMenuClick != null) {
                            mOnItemMenuClick.onItemMenuClick(childView, rv.getChildAdapterPosition(childView));
                        }
                        smoothHorizontalExpandOrCollapse(DEFAULT_DURATION / 2);
                        return true;
                    } else {
                        //拦截事件,防止targetView执行onClick事件
                        needIntercept = true;
                    }
                    //折叠菜单
                    smoothHorizontalExpandOrCollapse(DEFAULT_DURATION / 2);
                } else {
                    //当点击监听不为空 头部不在滑动状态  目标view不为空 且 点击不为头部控件时 才回调
                    if(mOnItemClick != null && !mRecyclerView.getRefreshHeader().isMove && mTargetView != null) {
                        int position = rv.getChildAdapterPosition(childView);
                        if(mRecyclerView.getHeadSize() < position) {
                            mOnItemClick.onItemClick(childView, position);
                        }
                    }
                }
                mRecyclerView.getRefreshHeader().isMove = false;
                mTargetView = null;
                break;
        }
        return needIntercept;
    }

    private boolean isExpanded() {
        if(mTargetView == null) {
            return false;
        }
        if(getHorizontalRange() == 0) { //此时没有拉伸按钮存在
            return false;
        }
        return mTargetView != null && mTargetView.getScrollX() == getHorizontalRange();
    }

    private boolean isCollapsed() {
        return mTargetView != null && mTargetView.getScrollX() == 0;
    }

    /*
    * 根据targetView的scrollX计算出targetView的偏移，这样能够知道这个point
    * 是在右侧的菜单中
     */
    private boolean inView(int x, int y) {
        if (mTargetView == null)
            return false;

        int scrollX = mTargetView.getScrollX();
        int left = mTargetView.getWidth() - scrollX;
        int top = mTargetView.getTop();
        int right = left + getHorizontalRange();
        int bottom = mTargetView.getBottom();
        Rect rect = new Rect(left, top, right, bottom);
        return rect.contains(x, y);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        if (mExpandAndCollapseAnim != null && mExpandAndCollapseAnim.isRunning() || mTargetView == null)
            return;
        if (mGestureDetector.onTouchEvent(e)) {
            mIsDragging = false;
            return;
        }

        int x = (int) e.getX();
        int action = MotionEventCompat.getActionMasked(e);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (mLastX - e.getX());
                if (mIsDragging) {
                    horizontalDrag(deltaX);
                }
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIsDragging) {
                    if (!smoothHorizontalExpandOrCollapse(0) && isCollapsed())
                        mTargetView = null;

                    mIsDragging = false;
                }
                break;
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    /**
     * 根据touch事件来滚动View的scrollX
     * @param delta
     */
    private void horizontalDrag(int delta) {
        int scrollX = mTargetView.getScrollX();
        int scrollY = mTargetView.getScrollY();
        if ((scrollX + delta) <= 0) {
            mTargetView.scrollTo(0, scrollY);
            return;
        }

        int horRange = getHorizontalRange();
        scrollX += delta;
        if (Math.abs(scrollX) < horRange) {
            mTargetView.scrollTo(scrollX, scrollY);
        } else {
            mTargetView.scrollTo(horRange, scrollY);
        }
    }

    /**
     * 根据当前scrollX的位置判断是展开还是折叠
     * @param velocityX 如果不等于0那么这是一次fling事件,否则是一次ACTION_UP或者ACTION_CANCEL
     */
    private boolean smoothHorizontalExpandOrCollapse(float velocityX) {
        if(mTargetView == null) {
            return false;
        }
        int scrollX = mTargetView.getScrollX();
        int scrollRange = getHorizontalRange();
        if (mExpandAndCollapseAnim != null)
            return false;

        int to = 0;
        int duration = DEFAULT_DURATION;

        if (velocityX == 0) {
            //如果已经展一半，平滑展开
            if (scrollX > scrollRange / 2) {
                to = scrollRange;
            }
        } else {
            if (velocityX > 0)
                to = 0;
            else
                to = scrollRange;

            duration = (int) ((1.f - Math.abs(velocityX) / mMaxVelocity) * DEFAULT_DURATION);
        }

        if (to == scrollX)
            return false;

        mExpandAndCollapseAnim = ObjectAnimator.ofInt(mTargetView, "scrollX", to);
        mExpandAndCollapseAnim.setDuration(duration);
        mExpandAndCollapseAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mExpandAndCollapseAnim = null;
                if (isCollapsed())
                    mTargetView = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mExpandAndCollapseAnim = null;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        mExpandAndCollapseAnim.start();
        return true;
    }

    public int getHorizontalRange() {
        if(mTargetView == null) {
            return 0;
        }
        RecyclerView.ViewHolder viewHolder = mCallback.getChildViewHolder(mTargetView);
        return mCallback.getHorizontalRange(viewHolder);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (Math.abs(velocityX) > mMinVelocity && Math.abs(velocityX) < mMaxVelocity) {
            if (!smoothHorizontalExpandOrCollapse(velocityX)) {
                if (isCollapsed())
                    mTargetView = null;
                return true;
            }
        }
        return false;
    }

    public interface Callback {

        int getHorizontalRange(RecyclerView.ViewHolder holder);

        RecyclerView.ViewHolder getChildViewHolder(View childView);

        View findTargetView(float x, float y);

    }
}
