package com.busycount.common.sample

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.BasicGlobalStyle
import com.busycount.core.ui.error.BasicErrorHandler
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/04/21
 * Describe :
 **/
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        BasicGlobalStyle.errorHandler = object : BasicErrorHandler() {

            override fun getLayoutId(): Int {
                return R.layout.main_load_failure
            }

            override fun onError(errorView: View, code: Int, msg: String, basicActivity: BasicActivity?) {
                super.onError(errorView, code, msg, basicActivity)
                val textView = errorView.findViewById<TextView>(R.id.tv_error)
                textView?.text = msg

                val needTitle = basicActivity?.customStyle?.needTitleBar ?: true
                errorView.findViewById<Group>(R.id.title_group).visibility = if (needTitle) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
                val view = errorView.findViewById<View>(R.id.container)
                UiFitUtil.fitTopOnce(view)

                errorView.findViewById<View>(R.id.iv_back).setOnClickListener {
                    basicActivity?.finish()
                }

            }
        }
    }
}