package com.busycount.core.ui

import android.R
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.busycount.core.const.BaseStyle
import com.busycount.core.utils.QMUIStatusBarHelper
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/02/05
 * Describe :BaseActivity
 **/
abstract class BaseActivity : AppCompatActivity() {

    val customStyle = BaseStyle()

    lateinit var rootViewGroup: ViewGroup;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootViewGroup = window.decorView.findViewById(R.id.content)
        initCustomStyle()
        initView()
        initTitleBar()
        initLogic()
    }


    private fun initCustomStyle() {
        setCustomStyle(customStyle)
        if (!customStyle.editMode) {
            QMUIStatusBarHelper.translucent(this)
        }
        if (customStyle.lightStatusBar) {
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        } else {
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        }
    }

    open fun setCustomStyle(style: BaseStyle) {
    }

    abstract fun initView()

    private fun initTitleBar() {
        if (!customStyle.needTitleBar) {
            return
        }
        val titleBar = setCustomTitleBar()

        if (rootViewGroup.childCount > 1) {
            val childAt = rootViewGroup.getChildAt(1)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            childAt.post {
                val titleHeight = titleBar.titleRootView.height
                layoutParams.topMargin = titleHeight
                childAt.layoutParams = layoutParams
            }
        }
    }

    abstract fun setCustomTitleBar(): BaseTitleBar


    abstract fun initLogic()
}