package com.e.helloworld.a_绘图.a_view的绘制.a_绘制动态心电图;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by：malei on 2016/12/6 19:31
 * Description：
 */
public class EcgViewBackground extends View {

    private final Paint mPaint;
    private final Path mPath;
    private int mWidth;  //view的宽
    private int mHeight;  //view的高
    protected int mGridWidth = 50;  //网格的宽度
    protected int mGridColor = Color.parseColor("#1b4200");  //网格颜色
    protected int mSGridWidth = 10;  //小网格的宽度
    protected int mSGridColor = Color.parseColor("#092100"); //小网格颜色
    protected int mBackgroundColor = Color.BLACK;

    public EcgViewBackground(Context context) {
        this(context,null);
    }

    public EcgViewBackground(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EcgViewBackground(Context context, AttributeSet attrs, int defStyleAttr) {
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
        initBackground(canvas);
    }

    //绘制view的背景
    private void initBackground(Canvas canvas){
        //将画布填满指定颜色
        canvas.drawColor(mBackgroundColor);
        //设置网格的颜色
        mPaint.setColor(mSGridColor);
        mPaint.setStrokeWidth(2);

        //小网格 竖线个数
        int vSNum = mWidth /mSGridWidth;
        //小网格 横线个数
        int hSNum = mHeight/mSGridWidth;
        //画小网格竖线
        for (int i = 0 ; i < vSNum+1 ; i++){
            canvas.drawLine(i*mSGridWidth, 0, i*mSGridWidth,mHeight,mPaint);
        }
        //画小网横线
        for (int i = 0 ; i < hSNum+1 ; i++){
            canvas.drawLine(0,i*mSGridWidth, mWidth,i*mSGridWidth,mPaint);
        }

        mPaint.setColor(mGridColor);
        mPaint.setStrokeWidth(2);
        //大网格 竖线个数
        int vNum = mWidth /mGridWidth;
        //大网格 横线个数
        int hNum = mHeight/mGridWidth;
        //画大网格竖线
        for (int i = 0 ; i < vNum+1 ; i++){
            canvas.drawLine(i*mGridWidth, 0, i*mGridWidth,mHeight,mPaint);
        }
        //画大网格横线
        for (int i = 0 ; i < hNum+1 ; i++){
            canvas.drawLine(0,i*mGridWidth, mWidth,i*mGridWidth,mPaint);
        }
    }
}

