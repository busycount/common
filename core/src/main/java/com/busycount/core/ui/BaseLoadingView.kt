package com.busycount.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author : thalys_ch
 * Date : 2021/03/01
 * Describe :
 **/
abstract class BaseLoadingView(private val baseActivity: BaseActivity) {

    private var loadingView: View? = null

    abstract fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            showLoadingView()
        } else {
            hideLoadingView()
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


}