package com.busycount.common;

import android.util.Log;

/**
 * L
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class L {

    public static void d(String tag, String msg) {
        //TODO
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void d(String msg) {
        d("L-D", msg);
    }

    public static void d(int aInt) {
        d("" + aInt);
    }

    public static void d(long aLong) {
        d("" + aLong);
    }

    public static void d(boolean aBoolean) {
        d("" + aBoolean);
    }

    public static void e(String msg) {
        e("L-E", msg);
    }
}
