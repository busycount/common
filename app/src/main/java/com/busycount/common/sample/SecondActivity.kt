package com.busycount.common.sample

import androidx.activity.viewModels
import com.busycount.common.sample.databinding.ActivityMainBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.title.BasicStyle
import com.busycount.core.ui.title.BasicTitleBar
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/04/21
 * Describe :
 **/
class SecondActivity : BasicActivity() {

    private val myVm: MyVm by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun initCreateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UiFitUtil.fitTop(binding.rootContainer)
    }

    override fun setCustomStyle(style: BasicStyle) {
        super.setCustomStyle(style)
        style.needTitleBar = false
    }

    override fun setCustomTitleBar(): BasicTitleBar {
        return TestTitle()
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

        titleBar.setTitleText("Sec Activity")

        binding.btnShowLoading.setOnClickListener {
            mockLoading(true)
        }

        supportFragmentManager.beginTransaction().add(R.id.container, MyFragment()).commit()
    }


    private fun mockLoading(showError: Boolean) {
        showLoading(true)
        myVm.status.value = true
        binding.btnShowLoading.postDelayed({
            showLoading(false)
            if (showError) {
                showError(1, "1")
            } else {
                hideError()
            }
            myVm.status.value = false
        }, 1500)
    }


    override fun onErrorRetry() {
        super.onErrorRetry()
        mockLoading(false)
    }
}