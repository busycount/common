package com.busycount.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * @author : BusyCount
 * Date : 2021/03/01
 * Describe :BaseLoadingView
 **/
abstract class BasicLoadingView(private val basicActivity: BasicActivity) {

    private var loadingView: View? = null

    private var errorView: View? = null

    abstract fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    abstract fun createErrorView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            showError(false)
            showLoadingView()
        } else {
            hideLoadingView()
        }
    }

    fun showError(isShow: Boolean) {
        if (isShow) {
            showLoading(false)
            showErrorView()
        } else {
            hideErrorView()
        }
    }

    private fun showLoadingView() {
        if (loadingView == null) {
            loadingView = createLoadingView(basicActivity.layoutInflater, basicActivity.rootViewGroup)
        }

        if (loadingView?.parent == null) {
            basicActivity.rootViewGroup.addView(loadingView)
        }
        loadingView?.visibility = View.VISIBLE
    }


    private fun hideLoadingView() {
        loadingView?.visibility = View.GONE
    }


    private fun showErrorView() {
        if (errorView == null) {
            errorView = createErrorView(basicActivity.layoutInflater, basicActivity.rootViewGroup)
        }

        if (errorView?.parent == null) {
            if (basicActivity.customStyle.needTitleBar) {
                val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                layoutParams.topMargin = basicActivity.titleBar.titleHeight
                errorView?.layoutParams = layoutParams
            }
            basicActivity.rootViewGroup.addView(errorView)
        }
        errorView?.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        errorView?.visibility = View.GONE
    }


    var retryListener: (() -> Unit)? = null


    abstract fun onError(code: Int, msg: String)

}