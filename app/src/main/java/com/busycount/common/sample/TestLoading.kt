package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicErrorView
import com.busycount.core.ui.BasicLoadingView

/**
 * @author : BusyCount
 * Date : 2021/03/01
 * Describe :TestLoading
 **/
class TestLoading(basicActivity: BasicActivity) : BasicLoadingView(basicActivity) {

    override fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        return layoutInflater.inflate(R.layout.main_loading, rootView, false)
    }

    override fun createErrorView(rootView: ViewGroup): BasicErrorView {
        return TestErrorView(rootView)
    }

    override fun onError(code: Int, msg: String) {

    }
}