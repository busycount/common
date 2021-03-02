package com.busycount.core.ui

import android.R
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.busycount.core.const.BaseStyle
import com.busycount.core.utils.QMUIStatusBarHelper

/**
 * @author : thalys_ch
 * Date : 2021/02/05
 * Describe :BaseActivity
 **/
abstract class BaseActivity : AppCompatActivity() {

    lateinit var rootViewGroup: ViewGroup

    val customStyle = BaseStyle()

    val titleBar: BaseTitleBar by lazy {
        setCustomTitleBar()
    }

    val loadingView: BaseLoadingView by lazy {
        setCustomLoadingView()
    }

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
            //编辑模式不使用透明状态栏
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
        titleBar.initTitle(this)
    }

    abstract fun setCustomTitleBar(): BaseTitleBar


    abstract fun setCustomLoadingView(): BaseLoadingView


    abstract fun initLogic()
}