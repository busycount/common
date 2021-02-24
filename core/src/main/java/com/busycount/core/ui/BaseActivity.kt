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

    private val customStyle = BaseStyle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val rootView = window.decorView.findViewById<ViewGroup>(R.id.content)
        if (customStyle.editMode) {
            titleBar.titleView.setPadding(0, 0, 0, 0)
            titleBar.titleHeight = titleBar.getDefaultTitleHeight()
        } else {
            UiFitUtil.fitTop(titleBar.titleView)
            titleBar.titleHeight = titleBar.getDefaultTitleHeight() + UiFitUtil.getStatusBarHeight(this)
        }

        rootView.addView(titleBar.titleView, 0)
        if (rootView.childCount > 1) {
            val childAt = rootView.getChildAt(1) as ViewGroup
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            childAt.post {
                val titleHeight = titleBar.titleView.height
                titleBar.titleHeight = titleHeight
                layoutParams.topMargin = titleHeight
                childAt.layoutParams = layoutParams
            }
        }
    }

    abstract fun setCustomTitleBar(): BaseTitleBar


    abstract fun initLogic()
}