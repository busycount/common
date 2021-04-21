package com.busycount.core.ui.loading

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.busycount.core.ui.BasicGlobalStyle


/**
 * @author : thalys_ch
 * Date : 2021/04/20
 * Describe :加载
 **/
class BasicLoadingDialog : DialogFragment() {

    private var showStartTime: Long = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(BasicGlobalStyle.loadingViewId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configWindow()
    }

    private fun configWindow() {
        val window = dialog?.window ?: return
        window.setGravity(Gravity.CENTER)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    fun show(manager: FragmentManager) {
        if (!isVisible) {
            showStartTime = System.currentTimeMillis()
        }
        show(manager, "BasicLoadingDialog")
    }


    override fun dismiss() {
        val interval = System.currentTimeMillis() - showStartTime - BasicGlobalStyle.minLoadingTime
        if (interval >= 0) {
            super.dismiss()
        } else {
            view?.postDelayed({
                dismiss()
            }, interval + 100)
        }
    }

}