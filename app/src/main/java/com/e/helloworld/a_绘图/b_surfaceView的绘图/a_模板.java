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
 * Create by：malei on 2016/12/6 22:01
 * Description：surface的模板一般就这样使用
 */
public abstract class a_模板 extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    protected SurfaceHolder mSurfaceHolder;
    private boolean isRun;
    protected Canvas mCanvas;
    protected int mWidth;
    protected int mHeight;
    protected Paint mPaint;

    protected abstract void drawViewInThread() ; //在线程中绘图

    public a_模板(Context context) {
        this(context,null);
    }

    public a_模板(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public a_模板(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRun = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        mHeight = height ;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }

    @Override
    public void run() {
        while (isRun){
            drawing();
        }
    }

    private void drawing() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);  //清屏
            drawViewInThread();
        }finally {
            if(mCanvas != null){
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
