package com.e.helloworld.c_交互篇.b_水平和竖直滚动;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.e.helloworld.z_工具箱.LogUtil;

/**
 * Create by：malei on 2016/12/7 14:53
 * Description：自定义ScrollView
 *
 *

 PanGestureScrollView scrollView = new PanGestureScrollView(this);
 LinearLayout layout = new LinearLayout(this);
 layout.setOrientation(LinearLayout.VERTICAL);
 for (int i = 0 ;i < 5 ; i++){
 ImageView iv = new ImageView(this);
 iv.setImageResource(R.mipmap.ic_launcher);
 layout.addView(iv,new LinearLayout.LayoutParams(1000,500));
 }
 scrollView.addView(layout);
 setContentView(scrollView);


 */
public class PanGestureScrollView extends FrameLayout{

    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private float mInterceptX;
    private float mInterceptY;
    private int mTouchSlop;

    public PanGestureScrollView(Context context) {
        this(context,null);
    }

    public PanGestureScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PanGestureScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LogUtil.i("PanGestureDetector 创建");
        mGestureDetector = new GestureDetector(context,mListener);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
    }

    /**
     * 让子视图绘制自己的大小。
     * 默认实现会让子视图和该视图一样大.
     * 重写：保证生成的子视图尽可能的大
     */
    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        //根据提供的大小和模型创建一个规范
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.leftMargin+lp.rightMargin, MeasureSpec.UNSPECIFIED);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.leftMargin+lp.rightMargin, MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    /**
     * 父控件要求子控件更新他们自己的值（mScrollX和mScrollY）
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断是否完成滚动和动画 。返回true的话，动画还为完成
        if(mScroller.computeScrollOffset()){
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            if(getChildCount() > 0){
                View child = getChildAt(0);
                x = clamp(x ,getWidth() - getPaddingRight() - getPaddingLeft(),
                        child.getWidth());
                y = clamp(y ,getWidth() - getPaddingBottom() - getPaddingTop(),
                        child.getHeight());
                if(x != oldX || y != oldY){
                    scrollTo(x,y);
                }
            }
            postInvalidate(); //动画完成前一直绘制
        }
    }

    //边界检查
    @Override
    public void scrollTo(int x, int y) {
        if(getChildCount() > 0){
            View child = getChildAt(0);
            x = clamp(x ,getWidth() - getPaddingRight() - getPaddingLeft(),
                    child.getWidth());
            y = clamp(y ,getWidth() - getPaddingBottom() - getPaddingTop(),
                    child.getHeight());
            if(x != getScrollX() || y != getScrollY()){
                super.scrollTo(x,y);
            }
        }
    }

    /**
     *拦截事件
     * 在这个方法中我们必须分清楚用户的触摸行为，从而确定是否是真正的拖动。
     * ACTION_DOWN和ACTION_MOVE的处理一起决定了手指的滑动距离，只有滑动的
     * 距离大于系统的触摸噶值常量，才能确定是一个拖动事件，并进行处理。
     *
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mInterceptX = ev.getX();
                mInterceptY = ev.getY();
                //将按下事件交给手势检测器
                mGestureDetector.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = ev.getX();
                float y = ev.getY();
                float xDiff = Math.abs(x - mInterceptX);
                float yDiff = Math.abs(y - mInterceptY);
                if(yDiff > mTouchSlop || xDiff > mTouchSlop){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //将触摸事件交给手势检测器
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private GestureDetector.SimpleOnGestureListener mListener = new GestureDetector.SimpleOnGestureListener(){

        //一个重复的轻击
        @Override
        public boolean onDown(MotionEvent e) {
            LogUtil.i("onDown");
            if(mScroller.isFinished()){
                mScroller.abortAnimation();//滑动完成，停止动画
            }
            return true;
        }

        //滑动，触摸屏按下后快速移动并抬起，会先触发滚动手势
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            fling((int)-velocityX/3,(int)-velocityY/3);  //动画效果
            return true;
        }

        //滚动，触摸屏按下后移动
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollBy((int)distanceX,(int)distanceY);
            return true;
        }
    };

    private void fling(int velocityX, int velocityY) {
        if(getChildCount() > 0){
            int height = getHeight() - getPaddingBottom() - getPaddingTop();
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            int bottom = getChildAt(0).getHeight();
            int right = getChildAt(0).getWidth();

            mScroller.fling(getScrollX(),getScrollY(),
                    velocityX,velocityY,
                    0,Math.max(0,right - width),
                    0,Math.max(0,bottom - height));
            invalidate();
        }
    }

    //进行边界检查的辅助方法
    private int clamp(int n, int my, int child) {
        if(my >= child || n<0){
            //子视图超过了父视图的边界或者小于父视图，不能滚动
            return 0;
        }
        if((my+n)>child){
            return child-my;
        }
        return n;
    }


}
