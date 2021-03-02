package com.busycount.core.ui

import android.R
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.busycount.core.utils.QMUIStatusBarHelper

/**
 * @author : BusyCount
 * Date : 2021/02/05
 * Describe :BaseActivity
 **/
abstract class BasicActivity : AppCompatActivity() {

    lateinit var rootViewGroup: ViewGroup

    val customStyle = BasicStyle()

    val titleBar: BasicTitleBar by lazy {
        setCustomTitleBar()
    }

    val loadingView: BasicLoadingView by lazy {
        setCustomLoadingView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootViewGroup = window.decorView.findViewById(R.id.content)
        initCustomStyle()
        initView()
        initTitleBar()
        initObserver()
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

    open fun setCustomStyle(style: BasicStyle) {
    }

    abstract fun initView()

    private fun initTitleBar() {
        if (!customStyle.needTitleBar) {
            return
        }
        titleBar.initTitle(this)
    }

    abstract fun setCustomTitleBar(): BasicTitleBar

    abstract fun setCustomLoadingView(): BasicLoadingView

    abstract fun initObserver()

    abstract fun initLogic()

    fun showLoading(isShow: Boolean) {
        loadingView.showLoading(isShow)
    }

    fun onError(code: Int, msg: String) {
        loadingView.showError(true)
        loadingView.onError(code, msg)
    }
}