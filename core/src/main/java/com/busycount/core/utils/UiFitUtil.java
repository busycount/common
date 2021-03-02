package com.busycount.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * @author : thalys_ch
 * Date : 2020/07/29
 * Describe :UiFitUtil
 **/
public class UiFitUtil {

    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //todo remove context
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 使顶部view下沉，避免被状态栏遮挡
     *
     * @param viewGroup 顶部的viewGroup
     */
    public static void fitTop(View viewGroup) {
        int left = viewGroup.getPaddingLeft();
        int top = viewGroup.getPaddingTop();
        int right = viewGroup.getPaddingRight();
        int bottom = viewGroup.getPaddingBottom();
        viewGroup.setPadding(left, top + getStatusBarHeight(viewGroup.getContext()), right, bottom);
    }

    /**
     * 使顶部view下沉，避免被状态栏遮挡
     *
     * @param v 顶部的view
     */
    public static void fitMargin(View v) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.topMargin = params.topMargin + getStatusBarHeight(v.getContext());
        v.requestLayout();
    }

    /**
     * @param v        View
     * @param uiHeight 默认状态栏高度
     */
    public static void fitHeight(View v, int uiHeight) {
        int offset = getStatusBarHeight(v.getContext()) - uiHeight;
        if (offset <= 0) {
            return;
        }
        int height = v.getHeight();
        if (height <= 0) {
            v.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    v.getViewTreeObserver().removeOnPreDrawListener(this);
                    ViewGroup.LayoutParams params = v.getLayoutParams();
                    params.height = v.getHeight() + offset;
                    v.setLayoutParams(params);
                    return false;
                }
            });
        } else {
            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.height = v.getHeight() + offset;
            v.setLayoutParams(params);
        }
    }


    /**
     * 控制textView 限时行数
     *
     * @param textView  TextView
     * @param limitLine 限定行数
     * @param listener  listener
     */
    public static void limitTextLine(TextView textView, int limitLine, TextLineLimitListener listener) {
        textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                textView.getViewTreeObserver().removeOnPreDrawListener(this);
                int lineCount = textView.getLineCount();
                listener.onLimit(lineCount > limitLine);
                textView.setMaxLines(limitLine);
                return true;
            }
        });
    }

    public interface TextLineLimitListener {
        void onLimit(boolean enable);
    }
}
