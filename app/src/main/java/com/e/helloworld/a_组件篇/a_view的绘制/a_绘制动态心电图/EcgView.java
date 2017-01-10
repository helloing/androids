package com.e.helloworld.a_组件篇.a_view的绘制.a_绘制动态心电图;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by：malei on 2016/12/6 17:47
 * Description：绘制动态心电图
 */
public class EcgView extends View {

    private final Paint mPaint;
    private final Path mPath;
    private int mWidth;  //view的宽
    private int mHeight;  //view的高
    protected int mLineColor = Color.parseColor("#76f112");

    public EcgView(Context context) {
        this(context,null);
    }

    public EcgView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EcgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();  //初始化画笔
        mPath = new Path();   //初始化
    }

    /**
     * 当View的布局大小改变时调用。
     * 获取自身的宽高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawEcg(canvas);
        scrollBy(1,0);  //移动速度
        invalidate();
//        postInvalidate();
//        postInvalidateDelayed(10);
    }

    private void drawEcg(Canvas canvas){
        mPath.reset();
        //移动到view左边界中心位置
        mPath.moveTo(0,mHeight/2);
        int tmp = 0;
        for(int i = 0 ;i < 10 ; i++){
            mPath.lineTo(tmp+20, 100);
            mPath.lineTo(tmp+70, mHeight / 2 + 50);
            mPath.lineTo(tmp+80, mHeight / 2);
            mPath.lineTo(tmp+200, mHeight / 2);
            tmp += 200;
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath,mPaint);
    }
}
