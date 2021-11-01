package com.busycount.exo.listener

/**
 * @author : thalys_ch
 * Date : 2021/11/01
 * Describe :Exo 控制器
 **/
interface ExoControllerListener {

    fun play()

    fun getProgress()

    fun seekTo(progress: Int)

}