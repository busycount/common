package com.busycount.common.sample

import com.busycount.common.sample.databinding.ActivityMainBinding
import com.busycount.core.ui.BaseActivity
import com.busycount.core.ui.BaseLoadingView
import com.busycount.core.ui.BaseTitleBar

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingView.retryListener = {
            mockLoading()
        }
    }

    override fun setCustomTitleBar(): BaseTitleBar {
        return TestTitle()
    }

    override fun setCustomLoadingView(): BaseLoadingView {
        return TestLoading(this)
    }

    override fun initLogic() {
        titleBar.setTitle("Main Activity")

        binding.btnShowLoading.setOnClickListener {
            mockLoading()
        }
    }


    private fun mockLoading() {
        loadingView.showLoading(true)
        binding.btnShowLoading.postDelayed({
            loadingView.showError(true)
        }, 3000)
    }
}