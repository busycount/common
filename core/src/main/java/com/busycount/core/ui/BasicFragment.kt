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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        initLogic()
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    abstract fun initObserver()

    abstract fun initLogic()

    fun showLoading(isShow: Boolean) {
        if (activity is BasicActivity) {
            (activity as BasicActivity).showLoading(isShow)
        }
    }

    fun onError(code: Int, msg: String) {
        if (activity is BasicActivity) {
            (activity as BasicActivity).onError(code, msg)
        }
    }

}