package com.willhua.tomatowork.utils;

/**
 * Created by lisan on 2016/11/13.
 */

public class LogUtil {
    private static final String TAG = "willhua";
    private static boolean SHOW_LOG = true;

    public static void i(String filter, String msg) {
        if (SHOW_LOG) {
            android.util.Log.i(TAG, filter + ":  " + msg);
        }
    }

    public static void d(String filter, String msg) {
        if (SHOW_LOG) {
            android.util.Log.d(TAG, filter + ":  " + msg);
        }
    }

    public static void e(String filter, String msg) {
        if (SHOW_LOG) {
            android.util.Log.e(TAG, filter + ":  " + msg);
        }
    }
}
