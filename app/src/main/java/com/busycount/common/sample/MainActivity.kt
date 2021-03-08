package com.busycount.common.sample

import androidx.activity.viewModels
import com.busycount.common.sample.databinding.ActivityMainBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicLoadingView
import com.busycount.core.ui.BasicTitleBar

class MainActivity : BasicActivity() {

    private val myVm: MyVm by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun initCreateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingView.onRetryListener = {
            mockLoading(false)
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

        titleBar.setTitleText("Main Activity")

        binding.btnShowLoading.setOnClickListener {
            mockLoading(true)
        }

        supportFragmentManager.beginTransaction().add(R.id.container, MyFragment()).commit()
    }


    private fun mockLoading(showError: Boolean) {
        loadingView.showLoading(true)
        myVm.status.value = true
        binding.btnShowLoading.postDelayed({
            loadingView.showError(showError)
            if (!showError) {
                loadingView.showLoading(false)
            }
            myVm.status.value = false
        }, 1500)
    }
}