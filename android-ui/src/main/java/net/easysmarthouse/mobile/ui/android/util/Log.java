package net.easysmarthouse.mobile.ui.android.util;

import android.text.TextUtils;

/**
 * Created by rusakovich on 26.01.2017.
 */
public class Log {

    public static final String TAG = "EasySmartHouse";

    private Log(){
    }

    public static void e(String msg){
        android.util.Log.e(TAG, getLocation() + msg);
    }

    public static void e(String msg, Throwable thr){
        android.util.Log.e(TAG, getLocation() + msg, thr);
    }

    public static void v(String msg) {
        android.util.Log.v(TAG, getLocation() + msg);
    }

    public static void i(String msg) {
        android.util.Log.i(TAG, getLocation() + msg);
    }

    public static void w(String msg) {
        android.util.Log.w(TAG, getLocation() + msg);
    }

    public static void d(String msg) {
        android.util.Log.d(TAG, getLocation() + msg);
    }

    private static String getLocation() {
        final String className = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;

        for (int i = 0; i < traces.length; i++) {
            StackTraceElement trace = traces[i];

            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                }
                else if (trace.getClassName().startsWith(className)) {
                    found = true;
                    continue;
                }
            }
            catch (ClassNotFoundException e) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }

}
