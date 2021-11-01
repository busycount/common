package com.busycount.common.sample.exo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.busycount.common.sample.R
import com.busycount.common.sample.databinding.ExoLayoutControllerBinding
import com.busycount.exo.listener.ExoControllerListener
import com.busycount.exo.listener.OnExoStateListener

/**
 * @author : thalys_ch
 * Date : 2021/11/01
 * Describe :ExoController
 **/
class ExoController @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener, OnExoStateListener {

    private val binding: ExoLayoutControllerBinding = ExoLayoutControllerBinding.inflate(LayoutInflater.from(context), this)

    private var controllerListener: ExoControllerListener? = null

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setOnClickListener(this)
        binding.startBtn.setOnClickListener(this)


        binding.bottomSeekProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    controllerListener?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.startBtn.id -> {
                controllerListener?.play()
            }
            else -> {
                execShow()
            }
        }
    }

    override fun onIdle(url: String) {
        binding.ivPoster.visibility = VISIBLE
        Glide.with(context).load(url).into(binding.ivPoster)
        binding.startBtn.visibility = VISIBLE
        binding.bottomController.visibility = GONE
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.startBtn.visibility = GONE
        } else {
            binding.startBtn.visibility = VISIBLE
        }
    }

    override fun onPlay() {
        binding.ivPoster.visibility = GONE
        binding.startBtn.visibility = GONE
        binding.bottomController.visibility = VISIBLE
        binding.startBtn.setImageResource(R.drawable.ic_controller_pause)
        execHide()
        updateProgress()
    }

    override fun onProgress(current: Long, total: Long, fromUser: Boolean) {
        binding.time.text = formatTime(current)
        binding.duration.text = formatTime(total)
        if (!fromUser) {
            val progress = current * 100 / total
            binding.bottomSeekProgress.progress = progress.toInt()
        }
    }

    override fun onPause() {
        removeCallbacks(hideJob)
        removeCallbacks(progressJob)
        binding.startBtn.visibility = VISIBLE
        binding.bottomController.visibility = VISIBLE
        binding.startBtn.setImageResource(R.drawable.ic_controller_play)
    }

    override fun onPlayEnd() {
        removeCallbacks(hideJob)
        removeCallbacks(progressJob)
        binding.startBtn.visibility = VISIBLE
        binding.bottomController.visibility = VISIBLE
        binding.bottomSeekProgress.progress = 100
        binding.time.text = binding.duration.text
        binding.startBtn.setImageResource(R.drawable.ic_controller_play)
    }

    override fun onError(msg: String) {
    }

    override fun setExoControllerListener(exoControllerListener: ExoControllerListener) {
        controllerListener = exoControllerListener
    }

    private fun execShow() {
        removeCallbacks(hideJob)
        binding.startBtn.visibility = VISIBLE
        execHide()
    }

    private fun execHide() {
        postDelayed(hideJob, 1500)
    }


    private val hideJob = Runnable {
        binding.startBtn.visibility = GONE
    }

    private fun updateProgress() {
        postDelayed(progressJob, 200)
    }

    private val progressJob = Runnable {
        controllerListener?.getProgress()
        updateProgress()
    }


    private fun formatTime(milliseconds: Long): String {
        var seconds = milliseconds / 1000
        val hours = seconds / 3600
        seconds -= hours * 3600
        val minutes = seconds / 60
        seconds -= minutes * 60

        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }


}