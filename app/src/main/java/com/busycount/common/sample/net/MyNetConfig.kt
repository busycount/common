package com.busycount.common.sample.net

import com.busycount.network.NetworkConfig
import retrofit2.Converter

/**
 * @author : thalys_ch
 * Date : 2021/08/09
 * Describe :
 **/
class MyNetConfig : NetworkConfig {

    override fun getBaseUrl(): String {
        return ""
    }

    override fun getConverterFactory(): Converter.Factory? {
        return SecurityConvertFactory.create(false)
    }


}