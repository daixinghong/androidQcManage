package com.sinano.base;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;


public class BaseApplication extends Application {


    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);

        mContext = getApplicationContext();

    }

    //获取上下文
    public static Context getContext(){

        return  mContext;
    }

}
