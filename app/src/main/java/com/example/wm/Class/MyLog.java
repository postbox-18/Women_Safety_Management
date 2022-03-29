package com.example.wm.Class;

import android.util.Log;

import com.example.wm.Interface.MyConstants;

public class MyLog {
    public static void d(String TAG, String message) {
        /**Note: During rename, if thsi fuctin calls itself, it will result in out of memory error*/
        if (MyConstants.IS_IN_DEBUG) {
            //MyLog.d(TAG, message); //this is not displaying sometimes
            Log.i(TAG, ""+message);
        }
    }

    public static void e(String TAG, String message) {
        if (MyConstants.IS_IN_DEBUG) {
            Log.e(TAG, ""+message);
        }
    }

    public static void i(String TAG, String message) {
        if (MyConstants.IS_IN_DEBUG) {
            Log.i(TAG, ""+message);
        }
    }
}
