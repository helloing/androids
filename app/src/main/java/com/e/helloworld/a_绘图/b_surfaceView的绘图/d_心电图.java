package com.e.helloworld.a_绘图.b_surfaceView的绘图;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Create by：malei on 2016/12/6 22:10
 * Description：
 */
public class d_心电图 extends a_模板{
    int tmp = 0;
    Path mPath = new Path();  //路径类

    public d_心电图(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawViewInThread()  {
        Log.i("TAG","绘制中...");
        scrollBy(1,0);  //移动速度
        mPath.reset();
        //移动到view左边界中心位置
        mPath.moveTo(0,mHeight/2);
        for(int i = 0 ;i < 10 ; i++){
            mPath.lineTo(tmp+20, 100);
            mPath.lineTo(tmp+70, mHeight / 2 + 50);
            mPath.lineTo(tmp+80, mHeight / 2);
            mPath.lineTo(tmp+200, mHeight / 2);
            tmp += 200;
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mCanvas.drawPath(mPath,mPaint);
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }
}
