package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.common.sample.databinding.MainLoadFailureBinding
import com.busycount.core.ui.BasicErrorView

/**
 * @author : thalys_ch
 * Date : 2021/03/08
 * Describe :
 **/
class TestErrorView(container: ViewGroup) : BasicErrorView(container) {

    private lateinit var errorBinding: MainLoadFailureBinding

    override fun initCreate(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        errorBinding = MainLoadFailureBinding.inflate(layoutInflater, rootView, false)
        return errorBinding.root
    }

    override fun initLogic() {
        errorBinding.ivError.setOnClickListener {
            onRetryListener?.invoke()
        }
    }

}