package com.busycount.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author : thalys_ch
 * Date : 2021/03/08
 * Describe :BasicErrorView
 **/
abstract class BasicErrorView(private val container: ViewGroup) {

    val errorView: View by lazy {
        onCreateView(LayoutInflater.from(container.context), container)
    }

    private fun onCreateView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        val view = initCreate(layoutInflater, rootView)
        initLogic()
        return view
    }

    abstract fun initCreate(layoutInflater: LayoutInflater, rootView: ViewGroup): View

    abstract fun initLogic()

    fun isNotAdded(): Boolean {
        return errorView.parent == null
    }

    // 避免容器为ScrollView，LinearLayout等，FrameLayout最好
    fun addToParent(layoutParams: ViewGroup.LayoutParams? = null) {
        if (isNotAdded()) {
            if (layoutParams != null) {
                container.addView(errorView, layoutParams)
            } else {
                container.addView(errorView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
    }

    fun showError() {
        errorView.visibility = View.VISIBLE
    }


    fun hideError() {
        errorView.visibility = View.GONE
    }

    var onRetryListener: (() -> Unit)? = null
}