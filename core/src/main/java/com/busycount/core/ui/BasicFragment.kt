package com.busycount.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.busycount.core.ui.error.BasicErrorHandler
import com.busycount.core.ui.error.BasicErrorView
import com.busycount.core.ui.error.OnErrorRetryListener

/**
 * @author : BusyCount
 * Date : 2021/03/02
 * Describe :BaseFragment
 **/
abstract class BasicFragment : Fragment(), OnErrorRetryListener {

    var errorContainer: ViewGroup? = null

    private var basicErrorView: BasicErrorView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        initLogic()
    }

    abstract fun initCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    open fun initObserver() {}

    abstract fun initLogic()

    open fun getErrorHandler(): BasicErrorHandler {
        return BasicGlobalStyle.errorHandler
    }

    fun showLoading(isShow: Boolean) {
        if (activity is BasicActivity) {
            (activity as BasicActivity).showLoading(isShow)
        }
    }

    fun showError(code: Int, msg: String) {
        if (errorContainer != null) {
            if (basicErrorView == null) {
                basicErrorView = BasicErrorView(this)
            }
            basicErrorView?.showError(code, msg)
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).showError(code, msg)
            }
        }
    }

    fun hidError() {
        if (errorContainer != null) {
            showLoading(false)
            basicErrorView?.hideError()
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).hideError()
            }
        }
    }

}