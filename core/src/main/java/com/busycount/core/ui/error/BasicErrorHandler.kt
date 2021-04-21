package com.busycount.core.ui.error

import android.view.View
import com.busycount.core.ui.BasicActivity

/**
 * @author : thalys_ch
 * Date : 2021/04/21
 * Describe :错误处理
 **/
open class BasicErrorHandler {
    open fun onError(errorView: View, code: Int, msg: String, basicActivity: BasicActivity? = null) {
    }
}