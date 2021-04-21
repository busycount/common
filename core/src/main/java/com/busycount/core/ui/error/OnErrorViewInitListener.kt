package com.busycount.core.ui.error

import androidx.annotation.LayoutRes
import com.busycount.core.ui.BasicGlobalStyle

/**
 * @author : thalys_ch
 * Date : 2021/04/21
 * Describe :ErrorView 初始化
 **/
interface OnErrorViewInitListener {

    @LayoutRes
    fun initErrorViewId(): Int {
        return BasicGlobalStyle.loadingViewId
    }

    fun initCreateErrorHandle(): BasicErrorHandler {
        return BasicGlobalStyle.errorHandler
    }


}