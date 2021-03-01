package com.busycount.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/02/24
 * Describe :BaseTitleBar
 **/
abstract class BaseTitleBar {

    var titleRootView: View? = null

    abstract fun createTitleView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    internal fun initTitle(baseActivity: BaseActivity) {
        titleRootView = createTitleView(baseActivity.layoutInflater, baseActivity.rootViewGroup)

        if (baseActivity.customStyle.editMode) {
            titleRootView?.setPadding(0, 0, 0, 0)
        } else {
            UiFitUtil.fitTop(titleRootView)
        }

        baseActivity.rootViewGroup.addView(titleRootView, 0)

        if (baseActivity.rootViewGroup.childCount > 1) {
            val childAt = baseActivity.rootViewGroup.getChildAt(1)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            childAt.post {
                val titleHeight = titleRootView!!.height
                layoutParams.topMargin = titleHeight
                childAt.layoutParams = layoutParams
            }
        }

        initLogic(baseActivity)
    }

    abstract fun initLogic(baseActivity: BaseActivity)

    fun setTitle(title: String) {
        if (titleRootView != null) {
            setTitleInner(title)
        } else {
            Log.w("BaseTitleBar", "BaseActivity-style-titleBar should be true")
        }
    }

    protected abstract fun setTitleInner(title: String)

}