package com.e.helloworld.c_交互篇.a_手动处理旋转;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.e.helloworld.R;
import com.e.helloworld.z_工具箱.LogUtil;

/**
 * Create by：malei on 2016/12/7 12:47
 * Description：
 * android:configChanges="orientation|keyboardHidden|screenSize"
 */
public class Code extends Activity{

    private String name;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        LogUtil.i("----------------------");
        LogUtil.i("onCtrate");
        name = getIntent().getStringExtra("name");
        initView();
    }

    private void initView() {
        textView = (TextView)this.findViewById(R.id.textView);
        textView.setText(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.i("onRestoreInstanceState");
        String data = savedInstanceState.getString("name");
        textView.setText(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i("onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.i("onSaveInstanceState");
        outState.putString("name","大哥");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i("onConfigurationChanged");
    }
}
