package com.busycount.common;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * AppContext
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class AppContext {

    private static Context appContext;

    public static void init(Application application) {
        appContext = application.getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static View inflate(@LayoutRes int layoutId) {
        return LayoutInflater.from(getAppContext()).inflate(layoutId, null);
    }

    public static View inflate(@LayoutRes int layoutId, ViewGroup group) {
        return LayoutInflater.from(getAppContext()).inflate(layoutId, group, false);
    }

    @ColorInt
    public static int getColor(@ColorRes int colorRes) {
        return getAppContext().getResources().getColor(colorRes);
    }

    public static Drawable getColorDrawable(@ColorRes int colorRes) {
        return new ColorDrawable(getColor(colorRes));
    }

    public static String getString(@StringRes int strRes) {
        return getAppContext().getResources().getString(strRes);
    }

    public static String getString(@StringRes int strRes, Object... formatArgs) {
        return getAppContext().getResources().getString(strRes, formatArgs);
    }

    public static int getPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getAppContext().getResources()
                .getDisplayMetrics());

    }
}
