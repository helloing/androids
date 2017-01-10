package com.e.helloworld.z_工具箱;

import android.content.Context;
import android.content.Intent;

/**
 * Create by：malei on 2016/12/7 13:07
 * Description：
 */
public class IntentUtil {

    public static void go(Context context, Class clzss){
        Intent intent = new Intent(context,clzss);
        context.startActivity(intent);
    }


    public static void go(Context context, Class clzss,String key,String value){
        Intent intent = new Intent(context,clzss);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }



}
