package com.busycount.exo

import android.util.Log
import com.busycount.exo.listener.ExoControllerListener
import com.busycount.exo.listener.OnExoStateListener
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

/**
 * @author : thalys_ch
 * Date : 2021/11/01
 * Describe :Exo
 **/
class ExoHelper(private val exoPlayer: ExoPlayer, private val exoState: OnExoStateListener) {

    private val playerListener: Player.EventListener = object : Player.EventListener {

        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            Log.d("-test-", "onIsLoadingChanged $isLoading")
            loadingListener?.invoke(isLoading)
            if (!exoPlayer.isPlaying) {
                exoState.onLoading(isLoading)
            }
        }

        //进度条拖动
        override fun onPositionDiscontinuity(reason: Int) {
            super.onPositionDiscontinuity(reason)
            exoState.onProgress(exoPlayer.currentPosition, exoPlayer.duration, true)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            Log.d("-test-", "onIsPlayingChanged $isPlaying " + exoPlayer.playbackState)
            if (isPlaying) {
                loadingListener?.invoke(false)
                exoState.onPlay()
            } else {
                if (exoPlayer.playbackState == Player.STATE_ENDED) {
                    exoState.onPlayEnd()
                } else {
                    exoState.onPause()
                }
            }
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            super.onPlayerError(error)
            exoState.onError(error.toString())
        }

    }


    private val controllerListener = object : ExoControllerListener {

        override fun play() {
            val state = exoPlayer.playbackState
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                if (state == Player.STATE_ENDED || state == Player.STATE_IDLE) {
                    execPlay()
                } else {
                    exoPlayer.play()
                }
            }
        }

        override fun getProgress() {
            exoState.onProgress(exoPlayer.currentPosition, exoPlayer.duration, false)
        }

        override fun seekTo(progress: Int) {
            val value = exoPlayer.duration * progress / 100
            exoPlayer.seekTo(value)
        }
    }

    init {
        exoPlayer.addListener(playerListener)
        exoState.setExoControllerListener(controllerListener)
    }


    private var mediaItem: MediaItem? = null

    fun setUrl(url: String?) {
        url?.let {
            mediaItem = MediaItem.fromUri(it)
        }
        exoState.onIdle(url ?: "")
    }

    private fun execPlay() {
        checkWifiListener?.invoke {
            mediaItem?.let {
                exoPlayer.setMediaItem(it)
                exoPlayer.prepare()
                exoPlayer.play()
            }
        }
    }

    var checkWifiListener: ((onAllow: () -> Unit) -> Unit)? = null

    var loadingListener: ((isLoading: Boolean) -> Unit)? = null

}