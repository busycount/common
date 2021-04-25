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

    private val dialogTag: String = System.currentTimeMillis().toString()

    private var showStartTime: Long = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(BasicGlobalStyle.loadingViewId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCancelable = false
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
        if (manager.isDestroyed) return
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit()
            show(manager, dialogTag)
        } catch (e: Exception) {
            //同一实例使用不同的tag会异常，这里捕获一下
            e.printStackTrace()
        }
    }


    override fun dismiss() {
        if (!isAdded || isDetached) {
            return
        }
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