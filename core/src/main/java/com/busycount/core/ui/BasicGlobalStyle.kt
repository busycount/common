package com.busycount.core.ui

import androidx.annotation.LayoutRes
import com.busycount.core.R
import com.busycount.core.ui.error.BasicErrorHandler

/**
 * @author : thalys_ch
 * Date : 2021/04/20
 * Describe :全局样式
 **/
object BasicGlobalStyle {

    @LayoutRes
    var loadingViewId: Int = R.layout.basic_layout_loading

    var minLoadingTime: Long = 500L

    var errorHandler: BasicErrorHandler = BasicErrorHandler()
}