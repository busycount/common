package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.common.sample.databinding.MainLoadFailureBinding
import com.busycount.core.ui.BaseActivity
import com.busycount.core.ui.BaseLoadingView

/**
 * @author : thalys_ch
 * Date : 2021/03/01
 * Describe :TestLoading
 **/
class TestLoading(baseActivity: BaseActivity) : BaseLoadingView(baseActivity) {

    private lateinit var errorBinding: MainLoadFailureBinding

    override fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        return layoutInflater.inflate(R.layout.main_loading, rootView, false)
    }

    override fun createErrorView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        errorBinding = MainLoadFailureBinding.inflate(layoutInflater, rootView, false)
        initErrorViewLogic()
        return errorBinding.root
    }

    private fun initErrorViewLogic() {
        errorBinding.ivError.setOnClickListener {
            retryListener?.invoke()
        }
    }
}