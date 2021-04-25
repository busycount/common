package com.busycount.core.ui.error

import android.view.View
import androidx.annotation.LayoutRes
import com.busycount.core.R
import com.busycount.core.ui.BasicActivity

/**
 * @author : thalys_ch
 * Date : 2021/04/21
 * Describe :错误处理
 **/
open class BasicErrorHandler {

    @LayoutRes
    open fun getLayoutId(): Int {
        return R.layout.basic_layout_error
    }

    open fun onError(errorView: View, code: Int, msg: String, basicActivity: BasicActivity? = null) {
    }
}