package com.busycount.common.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busycount.common.sample.databinding.MainTitlebarBinding
import com.busycount.core.ui.BaseActivity
import com.busycount.core.ui.BaseTitleBar
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/02/26
 * Describe :TestTitle
 **/
class TestTitle : BaseTitleBar() {

    private lateinit var binding: MainTitlebarBinding

    override fun getDefaultTitleBarHeight(): Int {
        return UiFitUtil.dp2px(49f)
    }

    override fun createTitleView(layoutInflater: LayoutInflater, rootView: ViewGroup): View {
        binding = MainTitlebarBinding.inflate(layoutInflater, rootView, false)
        return binding.root
    }

    override fun initLogic(baseActivity: BaseActivity) {
        binding.back.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    override fun setTitleInner(title: String) {
        binding.title.text = title
    }

}