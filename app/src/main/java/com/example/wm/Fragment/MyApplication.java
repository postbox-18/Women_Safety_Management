package com.example.wm.Fragment;


import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.example.wm.Class.MyLog;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MyApplication extends Application {
    private static final Executor mDiskIO = Executors.newSingleThreadExecutor();
    public static Executor diskIO(){
        return mDiskIO;
    }
    private String TAG=MyApplication.class.getSimpleName();
    private static Application instance;
    //https://developer.android.com/guide/background/threading
    private static final int DEFAULT_THREAD_POOL_SIZE=4;
    /**Use executor service for background processing instead of async tasks*/
    private static final ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

    /**To update UI, inside an executor, use this main thread handler*/
    private static final Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.i(TAG,"APPLICATION CLASS ONCREATE");
        instance = this;
    }

    public static Application getApplication(){
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    public static Executor getExecutor(){
        return executorService;
    }

    public static Handler getMainThreadHandler(){
        return mainThreadHandler;
    }
}