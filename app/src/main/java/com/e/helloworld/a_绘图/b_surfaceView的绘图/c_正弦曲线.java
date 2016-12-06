package com.e.helloworld.a_绘图.b_surfaceView的绘图;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Create by：malei on 2016/12/6 21:39
 * Description：
 */
public class c_正弦曲线 extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private Path mPath;
    private Paint mPaint;
    private boolean isRun;
    private Canvas mCanvas;
    private int x;
    private int y;

    public c_正弦曲线(Context context) {
        this(context,null);
    }

    public c_正弦曲线(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public c_正弦曲线(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);  //注册回调
        this.setKeepScreenOn(true);
        mPath = new Path();  //路径类
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRun = true;
        mPath.moveTo(0, 400);
        new Thread(this).start();  //启动绘制线程
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }

    @Override
    public void run() {
        while (isRun){
            mCanvas  = getHolder().lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            getHolder().unlockCanvasAndPost(mCanvas);//对画布内容进行提交
            x += 1;
            y = (int) (100*Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
        }
    }
}
