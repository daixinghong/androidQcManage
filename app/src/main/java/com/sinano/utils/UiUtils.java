package com.sinano.utils;

import android.os.Handler;
import android.os.Looper;

import com.sinano.base.BaseApplication;

public class UiUtils {

    //根据id找字符串
    public static String findStringBuId(int id) {
        return BaseApplication.getContext().getResources().getString(id);
    }

    //根据id获取颜色
    public static int findColorBuId(int id){
        return BaseApplication.getContext().getResources().getColor(id);
    }


    //根据id获取数组
    public static String[] findStringArrayBuId(int id){
        return BaseApplication.getContext().getResources().getStringArray(id);
    }

    //获取handler
    public static Handler getHandler(){
        Handler handler = new Handler(Looper.getMainLooper());
        return handler;
    }


}
