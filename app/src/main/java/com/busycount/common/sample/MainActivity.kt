package com.busycount.common.sample

import android.content.Intent
import androidx.activity.viewModels
import com.busycount.common.sample.databinding.ActivityMainBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.title.BasicTitleBar

class MainActivity : BasicActivity() {

    private val myVm: MyVm by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun initCreateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        titleBar.setTitleText("Main Activity")

        binding.btnShowLoading.setOnClickListener {
            mockLoading(true)
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
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