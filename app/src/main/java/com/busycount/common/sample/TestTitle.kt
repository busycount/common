package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.common.sample.databinding.MainTitlebarBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicTitleBar
import com.busycount.core.utils.UiFitUtil

/**
 * @author : BusyCount
 * Date : 2021/02/26
 * Describe :TestTitle
 **/
class TestTitle : BasicTitleBar() {

    private lateinit var binding: MainTitlebarBinding

    override fun getDefaultTitleBarHeight(): Int {
        return UiFitUtil.dp2px(49f)
    }

    override fun createTitleView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        binding = MainTitlebarBinding.inflate(layoutInflater, rootView, false)
        return binding.root
    }

    override fun initLogic(basicActivity: BasicActivity) {
        binding.back.setOnClickListener {
            basicActivity.onBackPressed()
        }
    }

    override fun setTitleInner(title: String) {
        binding.title.text = title
    }

}