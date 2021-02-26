package com.busycount.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.busycount.core.utils.UiFitUtil

/**
 * @author : thalys_ch
 * Date : 2021/02/24
 * Describe :BaseTitleBar
 **/
abstract class BaseTitleBar(@LayoutRes layoutId: Int, baseActivity: BaseActivity) {

    val titleRootView: View = baseActivity.layoutInflater.inflate(layoutId, baseActivity.rootViewGroup, false)

    init {
        if (baseActivity.customStyle.editMode) {
            titleRootView.setPadding(0, 0, 0, 0)
        } else {
            UiFitUtil.fitTop(titleRootView)
        }

        baseActivity.rootViewGroup.addView(titleRootView, 0)
    }

    abstract fun setTitle(title: String)

}