package com.e.helloworld.a_组件篇.b_surfaceView的绘图;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Create by：malei on 2016/12/6 20:53
 * Description：这是一个简单的练习类
 */
public class b_练习 extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread mThread;

    public b_练习(Context context) {
        this(context,null);
    }

    public b_练习(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public b_练习(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);  //监听组件的状态
        mThread = new DrawThread(holder); //创建绘图线程
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.setRun(true);
        mThread.start(); //启动绘图线程
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.setRun(false);  //组件销毁线程销毁
    }

    public class DrawThread extends Thread{

        private final SurfaceHolder mHolder;
        private boolean isRun;
        private int count;

        public DrawThread(SurfaceHolder holder) {
            mHolder = holder;
        }

        public void setRun(boolean isRun){
            this.isRun = isRun;
        }

        @Override
        public void run() {
            super.run();
            while (isRun){
                drawing(mHolder);
            }
        }

        private void drawing(SurfaceHolder mHolder) {
            Canvas canvas = mHolder.lockCanvas();  //获取画布
            canvas.drawColor(Color.WHITE); //画布背景色
            Paint p = new Paint();
            p.setColor(Color.BLACK);

            Rect r = new Rect(100, 50, 300, 250);
            canvas.drawRect(r, p);
            canvas.drawText("这是第" + (count++) + "秒", 100, 310, p);
            try {
                Thread.sleep(1000);// 睡眠时间为1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
