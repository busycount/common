package com.busycount.core.ui

import android.view.View

/**
 * @author : thalys_ch
 * Date : 2021/02/24
 * Describe :BaseTitleBar
 **/
abstract class BaseTitleBar {

    lateinit var titleView: View
        private set


    var titleHeight: Int = 0

    abstract fun getDefaultTitleHeight(): Int


}