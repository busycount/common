package com.busycount.common.sample.net

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author : thalys_ch
 * Date : 2021/08/09
 * Describe :
 **/
class SecurityConvertFactory private constructor(private val needEncrypt: Boolean) : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        return ResponseBodyConverter<Any>(type = type, needEncrypt = needEncrypt)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        Log.d("-test-", type.toString())

        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }


    internal class ResponseBodyConverter<T>(private val type: Type, private val needEncrypt: Boolean = false) : Converter<ResponseBody, T> {

        override fun convert(value: ResponseBody): T? {
            return value.use {
                var resultStr = it.string()
                if (needEncrypt) {
                    resultStr = EncryptHelper.decrypt(resultStr)
                }
                val jsonObject: JSONObject = JSONObject.parseObject(resultStr)
                val data: Any? = jsonObject.get("data")
                if (data == null) {
                    jsonObject["data"] = Any()
                    resultStr = jsonObject.toJSONString()
                }
                JSON.parseObject(resultStr, type)
            }
        }
    }

    companion object {
        fun create(needEncrypt: Boolean): SecurityConvertFactory {
            return SecurityConvertFactory(needEncrypt)
        }
    }

}


