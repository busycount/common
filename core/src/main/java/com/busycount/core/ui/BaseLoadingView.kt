package com.busycount.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * @author : thalys_ch
 * Date : 2021/03/01
 * Describe :BaseLoadingView
 **/
abstract class BaseLoadingView(private val baseActivity: BaseActivity) {

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
            loadingView = createLoadingView(baseActivity.layoutInflater, baseActivity.rootViewGroup)
        }

        if (loadingView?.parent == null) {
            baseActivity.rootViewGroup.addView(loadingView)
        }
        loadingView?.visibility = View.VISIBLE
    }


    private fun hideLoadingView() {
        loadingView?.visibility = View.GONE
    }


    private fun showErrorView() {
        if (errorView == null) {
            errorView = createErrorView(baseActivity.layoutInflater, baseActivity.rootViewGroup)
        }

        if (errorView?.parent == null) {
            if (baseActivity.customStyle.needTitleBar) {
                val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                layoutParams.topMargin = baseActivity.titleBar.titleHeight
                errorView?.layoutParams = layoutParams
            }
            baseActivity.rootViewGroup.addView(errorView)
        }
        errorView?.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        errorView?.visibility = View.GONE
    }


    var retryListener: (() -> Unit)? = null

}