package com.sinano.utils;

/**
 * 单例模式封装
 * Created by will.li on 2017/6/6.
 */
public abstract class Singleton<T> {
    private volatile T mInstance;

    protected abstract T create();

    public final T get() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }
            }
        }
        return mInstance;
    }
}