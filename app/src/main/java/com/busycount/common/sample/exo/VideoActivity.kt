package com.busycount.common.sample.exo

import com.busycount.common.sample.TestTitle
import com.busycount.common.sample.databinding.ExoActivityBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.title.BasicStyle
import com.busycount.core.ui.title.BasicTitleBar
import com.busycount.exo.ExoHelper
import com.google.android.exoplayer2.SimpleExoPlayer

//替换url
class VideoActivity : BasicActivity() {

    private lateinit var binding: ExoActivityBinding

    private lateinit var player: SimpleExoPlayer

    private lateinit var exoHelper: ExoHelper

    override fun setCustomStyle(style: BasicStyle) {
        super.setCustomStyle(style)
        style.needTitleBar = false
    }

    override fun initCreateView() {
        binding = ExoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setCustomTitleBar(): BasicTitleBar {
        return TestTitle()
    }

    override fun initLogic() {
        initPlayer()
    }

    private fun initPlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player

        exoHelper = ExoHelper(player, binding.controller)
        exoHelper.setUrl("url")
        exoHelper.loadingListener = { isLoading ->
            showLoading(isLoading)
        }
        exoHelper.checkWifiListener = {
//            if (NetworkUtils.isWifiConnected()) {
//                it.invoke()
//            } else {
//                CommonDialog.showConfirm(supportFragmentManager, "当前环境非wifi环境，继续播放将消耗流量，是否继续？", confirmListener = {
//                    it.invoke()
//                })
//            }
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }


    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

}