package com.busycount.common.sample.net

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author : thalys_ch
 * Date : 2021/08/09
 * Describe :
 **/
class SecurityConvertFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        return SecurityConvertFactory2.ResponseBodyConverter<Any>(type = type, needEncrypt = false)
    }



    internal class ResponseBodyConverter<T>(private val type: Type, private val needEncrypt: Boolean = false) : Converter<ResponseBody, T> {

        override fun convert(value: ResponseBody): T? {
            return value.use {
                var resultStr = it.string()
//            if (needEncrypt) {
//                resultStr = EncryptHelper.decrypt(resultStr)
//            }
                val jsonObject: JSONObject = JSONObject.parseObject(resultStr)
                val data: Any? = jsonObject.get("data")
                if (data == null) {
                    jsonObject.put("data", Any())
                    resultStr = jsonObject.toJSONString()
                }
                JSON.parseObject(resultStr, type)
            }
        }
    }

//    companion object {
//        fun create(needEncrypt: Boolean): SecurityConvertFactory {
//            return SecurityConvertFactory(needEncrypt)
//        }
//    }

}


