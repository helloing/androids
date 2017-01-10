package com.e.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.helloworld.c_交互篇.b_水平和竖直滚动.PanGestureScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

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
    }
}
