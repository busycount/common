package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.common.sample.databinding.MainLoadingBinding
import com.busycount.core.ui.BaseActivity
import com.busycount.core.ui.BaseLoadingView

/**
 * @author : thalys_ch
 * Date : 2021/03/01
 * Describe :
 **/
class TestLoading(baseActivity: BaseActivity) : BaseLoadingView(baseActivity) {

    override fun createLoadingView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        return layoutInflater.inflate(R.layout.main_loading, rootView, false)
    }

}