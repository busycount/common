package com.busycount.common;

/**
 * DoubleClickUtil
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class DoubleClickUtil {

    private static final long INTERVAL = 600L;
    private static long lastClickTime;

    public static boolean isDoubleClick() {
        long nowTime = System.currentTimeMillis();
        boolean isDouble = nowTime - lastClickTime < INTERVAL;
        lastClickTime = nowTime;
        return isDouble;
    }
}
