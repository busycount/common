package com.busycount.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.busycount.core.ui.error.BasicErrorView
import com.busycount.core.ui.error.OnErrorRetryListener

/**
 * @author : BusyCount
 * Date : 2021/03/02
 * Describe :BaseFragment
 **/
abstract class BasicFragment : Fragment(), OnErrorRetryListener {

    private var errorContainer: ViewGroup? = null

    private var basicErrorView: BasicErrorView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        errorContainer = initSelfError()
        initObserver()
        initLogic()
    }

    abstract fun initCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    open fun initObserver() {}

    abstract fun initLogic()

    open fun initSelfError(): ViewGroup? {
        return null
    }

    fun showLoading(isShow: Boolean) {
        if (activity is BasicActivity) {
            (activity as BasicActivity).showLoading(isShow)
        }
    }

    fun showError(code: Int, msg: String) {
        if (errorContainer != null) {
            if (basicErrorView == null) {
                basicErrorView = BasicErrorView(errorContainer!!)
            }
            basicErrorView?.let {
                it.attach(onErrorRetryListener = this)
                BasicGlobalStyle.errorHandler.onError(it.errorView, code, msg)
                it.showError()
            }
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).showError(code, msg)
            }
        }
    }

    fun hidError() {
        if (errorContainer != null) {
            basicErrorView?.hideError()
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).hideError()
            }
        }
    }

}