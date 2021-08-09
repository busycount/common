package com.busycount.common.sample.net

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author : thalys_ch
 * Date : 2021/08/09
 * Describe :
 */
class SecurityConvertFactory2 private constructor(
    /**
     * 是否需要加密
     */
    private val needEncrypt: Boolean
) : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        return ResponseBodyConverter<Any>(type, needEncrypt)
    }

    /**
     * 自定义响应ResponseBody
     */
    internal class ResponseBodyConverter<T>(private val type: Type, private val needEncrypt: Boolean) : Converter<ResponseBody, T> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): T {
            return value.use { value ->
                var resultStr = value.string()
                if (needEncrypt) {
        //                    resultStr = EncryptHelper.decrypt(resultStr);
                }
                val jsonObject = JSONObject.parseObject(resultStr)
                val data = jsonObject["data"]
                if (data == null) {
                    jsonObject["data"] = Any()
                    resultStr = jsonObject.toJSONString()
                }
                JSON.parseObject(resultStr, type)
            }
        }
    }

    companion object {
        fun create(needEncrypt: Boolean): SecurityConvertFactory2 {
            return SecurityConvertFactory2(needEncrypt)
        }
    }
}