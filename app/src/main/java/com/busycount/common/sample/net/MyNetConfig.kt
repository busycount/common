package com.busycount.common.sample.net

import com.busycount.network.NetworkConfig

/**
 * @author : thalys_ch
 * Date : 2021/08/09
 * Describe :
 **/
class MyNetConfig : NetworkConfig {

    override fun getBaseUrl(): String {
        return "https://lhisdev.thalys.net.cn/"
    }




}