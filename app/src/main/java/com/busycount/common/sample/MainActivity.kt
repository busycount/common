package com.busycount.common.sample

import androidx.activity.viewModels
import com.busycount.common.sample.databinding.ActivityMainBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicLoadingView
import com.busycount.core.ui.BasicTitleBar

class MainActivity : BasicActivity() {

    private val myVm: MyVm by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingView.retryListener = {
            mockLoading()
        }
    }

    override fun setCustomTitleBar(): BasicTitleBar {
        return TestTitle()
    }

    override fun setCustomLoadingView(): BasicLoadingView {
        return TestLoading(this)
    }

    override fun initObserver() {
    }

    override fun initLogic() {
        myVm.status.observe(this) {
            binding.tv.text = if (it) {
                "True"
            } else {
                "false"
            }
        }

        titleBar.setTitle("Main Activity")

        binding.btnShowLoading.setOnClickListener {
            mockLoading()
        }
    }


    private fun mockLoading() {
        loadingView.showLoading(true)
        myVm.status.value = true
        binding.btnShowLoading.postDelayed({
            loadingView.showError(true)
            myVm.status.value = false
        }, 3000)
    }
}