package com.busycount.core.ui.error

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicGlobalStyle

/**
 * @author : thalys_ch
 * Date : 2021/03/08
 * Describe :BasicErrorView
 **/
class BasicErrorView(private val container: ViewGroup) {

    val errorView: View by lazy {
        LayoutInflater.from(container.context).inflate(BasicGlobalStyle.errorViewId, container, false)
    }

    // 避免容器为ScrollView，LinearLayout等，FrameLayout最好
    fun attach(activity: BasicActivity? = null, onErrorRetryListener: OnErrorRetryListener? = null) {
        if (errorView.parent != null) {
            return
        }

        if (activity != null) {
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            layoutParams.topMargin = activity.titleBar.titleHeight
            container.addView(errorView, layoutParams)
        } else {
            container.addView(errorView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        errorView.isClickable = true
        errorView.isFocusable = true
        errorView.setOnClickListener {
            onErrorRetryListener?.onErrorRetry()
        }
    }

    fun showError() {
        errorView.visibility = View.VISIBLE
    }

    fun hideError() {
        errorView.visibility = View.GONE
    }

}