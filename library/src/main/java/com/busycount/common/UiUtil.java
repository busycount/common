package com.busycount.common;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * UiUtil
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class UiUtil {

    public static void fitStatus(@NonNull Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    public static void fitStatus(@NonNull Activity activity, @ColorInt int color, boolean isDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(color);
                View decorView = window.getDecorView();
                if (isDark) {
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    decorView.setSystemUiVisibility(0);
                }
            } else {
                if (isDark) {
                    //改不了图标颜色，就修改状态栏颜色
                    float[] fc = new float[3];
                    Color.colorToHSV(color, fc);
                    fc[2] *= 0.7;
                    window.setStatusBarColor(Color.HSVToColor(fc));
                } else {
                    window.setStatusBarColor(color);
                }
            }
        }
    }

    public static void fullScreen(Window window) {
        if (window == null) {
            return;
        }
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE //
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }

    public static void fullDialog(Window window, Drawable drawable) {
        if (window == null) {
            return;
        }
        window.setBackgroundDrawable(drawable);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        fullScreen(window);
    }
}
