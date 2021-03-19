package com.busycount.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author : BusyCount
 * Date : 2021/03/02
 * Describe :BaseFragment
 **/
abstract class BasicFragment : Fragment() {

    protected var errorContainer: ViewGroup? = null

    private var errorView: BasicErrorView? = null

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

    fun showLoading(isShow: Boolean) {
        if (activity is BasicActivity) {
            (activity as BasicActivity).showLoading(isShow)
        }
    }

    open fun initErrorViewCreate(): BasicErrorView? {
        return null
    }

    open fun initErrorViewLogic(errorView: BasicErrorView) {

    }

    private fun selfDisplayError(): Boolean {
        return errorContainer != null
    }

    fun showError(code: Int, msg: String) {
        if (selfDisplayError()) {
            if (errorContainer == null) {
                return
            }
            if (errorView == null) {
                errorView = initErrorViewCreate()
                errorView?.let {
                    initErrorViewLogic(it)
                }
            }
            errorView?.addToParent()
            errorView?.showError()
            errorView?.onError(code, msg)
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).showError(code, msg)
            }
        }
    }

    fun hidError() {
        if (selfDisplayError()) {
            errorView?.hideError()
        } else {
            if (activity is BasicActivity) {
                (activity as BasicActivity).hideError()
            }
        }
    }

}