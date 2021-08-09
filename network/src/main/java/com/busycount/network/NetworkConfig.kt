package com.busycount.network

import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * @author : thalys_ch
 * Date : 2021/07/28
 * Describe :
 **/
interface NetworkConfig {

    fun getBaseUrl(): String


    fun getConnectTimeoutSecond(): Long {
        return 30
    }

    fun getReadTimeoutSecond(): Long {
        return 10
    }

    fun getWriteTimeoutSecond(): Long {
        return 10
    }

    fun isDebug(): Boolean {
        return false
    }


    fun getConverterFactory(): Converter.Factory? {
        return null
    }

    fun getCallAdapterFactory(): CallAdapter.Factory? {
        return null
    }

    fun getCustomRetrofitBuilder(): Retrofit? {
        return null
    }


}