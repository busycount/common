package com.busycount.core.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.busycount.core.ui.error.BasicErrorHandler
import com.busycount.core.ui.error.BasicErrorView
import com.busycount.core.ui.error.OnErrorRetryListener
import com.busycount.core.ui.loading.BasicLoadingDialog
import com.busycount.core.ui.title.BasicStyle
import com.busycount.core.ui.title.BasicTitleBar
import com.busycount.core.utils.QMUIStatusBarHelper

/**
 * @author : BusyCount
 * Date : 2021/02/05
 * Describe :BaseActivity
 **/
abstract class BasicActivity : AppCompatActivity(), OnErrorRetryListener {

    lateinit var rootViewGroup: ViewGroup

    val customStyle = BasicStyle()

    val titleBar: BasicTitleBar by lazy {
        setCustomTitleBar()
    }

    private val basicLoadingDialog: BasicLoadingDialog by lazy {
        BasicLoadingDialog()
    }

    private val basicErrorView: BasicErrorView by lazy {
        BasicErrorView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootViewGroup = window.decorView.findViewById(android.R.id.content)
        initCustomStyle()
        initCreateView()
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

    abstract fun initCreateView()

    private fun initTitleBar() {
        if (!customStyle.needTitleBar) {
            return
        }
        titleBar.initTitle(this)
    }

    abstract fun setCustomTitleBar(): BasicTitleBar

    open fun getErrorHandler(): BasicErrorHandler {
        return BasicGlobalStyle.errorHandler
    }

    open fun initObserver() {
    }

    abstract fun initLogic()

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            basicLoadingDialog.show(supportFragmentManager)
        } else {
            basicLoadingDialog.dismiss()
        }
    }

    fun showError(code: Int, msg: String) {
        basicErrorView.showError(code, msg)
    }

    fun hideError() {
        showLoading(false)
        basicErrorView.hideError()
    }

}