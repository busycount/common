package com.busycount.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;

import static com.busycount.common.AppContext.getAppContext;

/**
 * SpUtil
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class SpUtil {

    //todo dynamic
    private static final String SP_NAME = "-test-";

    public static SharedPreferences getSp(String name) {
        return getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSp() {
        return getSp(SP_NAME);
    }

    public static SharedPreferences.Editor edit() {
        return getSp().edit();
    }

    public static boolean getBoolean(String key, boolean def) {
        return getSp().getBoolean(key, def);
    }

    public static int getInt(String key, int def) {
        return getSp().getInt(key, def);
    }

    public static float getFloat(String key, float def) {
        return getSp().getFloat(key, def);
    }

    public static long getLong(String key, long def) {
        return getSp().getLong(key, def);
    }

    public static String getString(String key, String def) {
        return getSp().getString(key, def);
    }

    public static void clear() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            clear24(SP_NAME);
        } else {
            clearL(SP_NAME);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void clear24(String name) {
        getAppContext().deleteSharedPreferences(name);
    }

    private static void clearL(String name) {
        getSp(name).edit().clear().apply();
    }
}

