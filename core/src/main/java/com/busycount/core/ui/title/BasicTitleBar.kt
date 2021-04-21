package com.busycount.core.ui.title

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.busycount.core.ui.BasicActivity
import com.busycount.core.utils.UiFitUtil

/**
 * @author : BusyCount
 * Date : 2021/02/24
 * Describe :BaseTitleBar
 **/
abstract class BasicTitleBar {

    var titleRootView: View? = null
        protected set

    var titleHeight = 0
        private set

    protected abstract fun getDefaultTitleBarHeight(): Int

    protected abstract fun createTitleView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    internal fun initTitle(basicActivity: BasicActivity) {
        titleRootView = createTitleView(basicActivity.layoutInflater, basicActivity.rootViewGroup)

        titleHeight = getDefaultTitleBarHeight()
        if (basicActivity.customStyle.editMode) {
            titleRootView?.setPadding(0, 0, 0, 0)
        } else {
            UiFitUtil.fitTop(titleRootView)
            titleHeight = getDefaultTitleBarHeight() + UiFitUtil.getStatusBarHeight(basicActivity)
        }

        basicActivity.rootViewGroup.addView(titleRootView, 0)

        if (basicActivity.rootViewGroup.childCount > 1) {
            val childAt = basicActivity.rootViewGroup.getChildAt(1)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            childAt.post {
                titleHeight = titleRootView!!.height
                layoutParams.topMargin = titleHeight
                childAt.layoutParams = layoutParams
            }
        }

        initLogic(basicActivity)
    }

    protected abstract fun initLogic(basicActivity: BasicActivity)

    protected fun hasInit(): Boolean {
        val isInit = titleRootView != null
        if (!isInit) {
            Log.w("Basic-Core", "BasicActivity-style-titleBar should be true")
        }
        return isInit
    }

    abstract fun setTitleText(title: String?)

}