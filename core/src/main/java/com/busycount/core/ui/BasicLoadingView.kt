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
abstract class BasicLoadingView(protected val basicActivity: BasicActivity) {

    private var loadingView: View? = null

    private var errorView: BasicErrorView? = null

    abstract fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    abstract fun createErrorView(rootView: ViewGroup): BasicErrorView

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
            errorView = createErrorView(basicActivity.rootViewGroup)
        }
        if (errorView?.isNotAdded() == true) {
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            layoutParams.topMargin = basicActivity.titleBar.titleHeight
            errorView?.addToParent(layoutParams)
            errorView?.onRetryListener = onRetryListener
        }

        errorView?.showError()
    }


    private fun hideErrorView() {
        errorView?.hideError()
    }


    var onRetryListener: (() -> Unit)? = null
        set(value) {
            errorView?.onRetryListener = value
            field = value
        }


    fun onError(code: Int, msg: String) {
        errorView?.onError(code, msg)
    }

}