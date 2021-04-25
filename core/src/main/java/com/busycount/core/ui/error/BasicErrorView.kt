package com.busycount.core.ui.error

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicFragment

/**
 * @author : thalys_ch
 * Date : 2021/03/08
 * Describe :BasicErrorView
 **/
class BasicErrorView {

    private var fragment: BasicFragment? = null
    private var activity: BasicActivity? = null

    private var errorView: View? = null

    private val errorHandler: BasicErrorHandler

    constructor(activity: BasicActivity) {
        this.activity = activity
        errorHandler = activity.getErrorHandler()
    }

    constructor(fragment: BasicFragment) {
        this.fragment = fragment
        errorHandler = fragment.getErrorHandler()
    }

    private fun initErrorView() {
        if (errorView != null) {
            return
        }
        activity?.let {
            initCreateView(it.layoutInflater, it.rootViewGroup)
        }
        fragment?.errorContainer?.let {
            initCreateView(fragment!!.layoutInflater, it)
        }
    }

    private fun initCreateView(layoutInflater: LayoutInflater, container: ViewGroup) {
        errorView = layoutInflater.inflate(errorHandler.getLayoutId(), container, false)
    }

    // 避免容器为ScrollView，LinearLayout等，FrameLayout最好
    private fun attach() {
        initErrorView()

        if (errorView == null || errorView?.parent != null) {
            return
        }

        if (activity != null) {
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            layoutParams.topMargin = activity!!.titleBar.titleHeight
            activity!!.rootViewGroup.addView(errorView, layoutParams)
        } else {
            fragment?.errorContainer?.addView(errorView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        errorView?.isClickable = true
        errorView?.isFocusable = true
        errorView?.setOnClickListener {
            activity?.onErrorRetry()
            fragment?.onErrorRetry()
        }
    }

    fun showError(code: Int, msg: String) {
        attach()
        errorView?.let {
            errorHandler.onError(it, code, msg, activity)
            it.visibility = View.VISIBLE
        }
    }

    fun hideError() {
        errorView?.visibility = View.GONE
    }

}