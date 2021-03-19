package com.busycount.common.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.busycount.common.sample.databinding.FragmentABinding
import com.busycount.core.ui.BasicErrorView
import com.busycount.core.ui.BasicFragment

/**
 * @author : thalys_ch
 * Date : 2021/03/08
 * Describe :
 **/
class MyFragment : BasicFragment() {

    private lateinit var binding: FragmentABinding

    override fun initCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentABinding.inflate(layoutInflater, container, false)
        errorContainer = binding.secContainer
        return binding.root
    }

    override fun initLogic() {
        binding.showError.setOnClickListener {
            showError(1, "1")
        }
        binding.showLoading.setOnClickListener {
            showLoading(true)
            binding.showLoading.postDelayed({ showLoading(false) }, 1000)
        }
    }

    override fun initErrorViewCreate(): BasicErrorView? {
        return if (errorContainer != null) {
            TestErrorView(errorContainer!!)
        } else {
            super.initErrorViewCreate()
        }
    }

    override fun initErrorViewLogic(errorView: BasicErrorView) {
        super.initErrorViewLogic(errorView)
        errorView.onRetryListener = {
            Toast.makeText(context, "abc", Toast.LENGTH_SHORT).show()
            hidError()
        }
    }

}