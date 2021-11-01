package com.busycount.exo.listener

/**
 * @author : thalys_ch
 * Date : 2021/11/01
 * Describe :Exo 状态监听
 **/
interface OnExoStateListener {

    fun onIdle(url: String)

    fun onLoading(isLoading: Boolean)

    fun onPlay()

    fun onProgress(current: Long, total: Long, fromUser: Boolean)

    fun onPause()

    fun onPlayEnd()

    fun onError(msg: String)

    fun setExoControllerListener(exoControllerListener: ExoControllerListener)

}