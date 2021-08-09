package com.busycount.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @author : thalys_ch
 * Date : 2021/07/28
 * Describe :
 **/
class NetClient private constructor(private val config: NetworkConfig) {

    companion object {
        private var instance: NetClient? = null

        fun onInit(networkConfig: NetworkConfig) {
            instance = NetClient(networkConfig)
        }

        fun get(): NetClient {
            if (instance == null) {
                throw IllegalStateException("Do init at application first")
            }
            return instance!!
        }
    }


    private val retrofit: Retrofit by lazy {
        initRetrofit()
    }


    private fun initRetrofit(): Retrofit {

        if (config.getCustomRetrofitBuilder() != null) {
            return config.getCustomRetrofitBuilder()!!
        }

        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(config.getConnectTimeoutSecond(), TimeUnit.SECONDS)
            .readTimeout(config.getReadTimeoutSecond(), TimeUnit.SECONDS)
            .writeTimeout(config.getWriteTimeoutSecond(), TimeUnit.SECONDS)


        if (config.isDebug()) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addNetworkInterceptor(logInterceptor)
        }


        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(config.getBaseUrl())
            .client(clientBuilder.build())

        config.getConverterFactory()?.let {
            builder.addConverterFactory(it)
        }

        config.getCallAdapterFactory()?.let {
            builder.addCallAdapterFactory(it)
        }

        return builder.build()
    }


}