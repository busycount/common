package com.busycount.common.sample.net

import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptHelper {
    /**
     * 密钥
     */
    const val PRIVATE_KEY = "MIIBIjANBgkqhkiG"

    /**
     * 编码
     */
    private val CHAR_SET = StandardCharsets.UTF_8

    /**
     * AES加密
     */
    private const val ALGORITHM = "AES/ECB/PKCS7Padding"

    /**
     * 密钥长度
     */
    private const val KEY_BIT_SIZE = 128

    /**
     * sign处理
     *
     * @param encryptedJsonQ 加密过的q的值
     * @return
     */
    fun encryptSign(encryptedJsonQ: String): String {
        val value = encryptedJsonQ + PRIVATE_KEY
        var md5Result: ByteArray? = ByteArray(0)
        try {
            md5Result = encryptMD5(value.toByteArray(StandardCharsets.UTF_8))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        require(md5Result!!.size == 16) { "MD5加密结果字节数组错误" }
        val first = Math.abs(bytesToInt(md5Result, 0))
        val second = Math.abs(bytesToInt(md5Result, 4))
        val third = Math.abs(bytesToInt(md5Result, 8))
        val fourth = Math.abs(bytesToInt(md5Result, 12))
        return String.format(Locale.CHINA, "%d%d%d%d", first, second, third, fourth)
    }

    /**
     * 加密q的json串。
     *
     * @param json 原始字符串
     * @return 加密结果字符串
     */
    fun encrypt(json: String): String {
        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, initKey())
            val encryptResult = cipher.doFinal(json.toByteArray(CHAR_SET))
            val unsafeStr = String(Base64.encode(encryptResult, Base64.NO_WRAP), CHAR_SET)
            return unsafeStr.replace('+', '-').replace('/', '_')
        } catch (e: Exception) {
            Log.d("SecurityTools", "敏感数据加密错误 " + e.message)
        }
        return json
    }

    /**
     * 解密字符串
     *
     * @param target 目标
     */
    fun decrypt(target: String): String {
        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, initKey())
            val unsafeStr = target.replace('-', '+').replace('_', '/')
            val decryptResult = cipher.doFinal(Base64.decode(unsafeStr.toByteArray(CHAR_SET), Base64.DEFAULT))
            return String(decryptResult, CHAR_SET)
        } catch (e: Exception) {
            Log.d("SecurityTools", "敏感数据解密错误 " + e.message)
        }
        return target
    }

    /**
     * 初始化密钥
     */
    private fun initKey(): SecretKeySpec {
        val keys = PRIVATE_KEY.toByteArray(CHAR_SET)
        val bytes = ByteArray(KEY_BIT_SIZE / 8)
        for (i in bytes.indices) {
            if (keys.size > i) {
                bytes[i] = keys[i]
            } else {
                bytes[i] = 0
            }
        }
        return SecretKeySpec(bytes, "AES")
    }

    private fun bytesToInt(src: ByteArray, offset: Int): Int {
        return ((src[offset].toInt() and 0xFF).shl(24))
            .or((src[offset + 1].toInt() and 0xFF).shl(16))
            .or((src[offset + 2].toInt() and 0xFF).shl(8))
            .or((src[offset + 3].toInt() and 0xFF))
    }

    private fun encryptMD5(data: ByteArray): ByteArray? {
        return hashTemplate(data, "MD5")
    }

    private fun hashTemplate(data: ByteArray?, algorithm: String): ByteArray? {
        return if (data == null || data.isEmpty()) null else try {
            val md = MessageDigest.getInstance(algorithm)
            md.update(data)
            md.digest()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }


    fun paramsGet(parameter: Any?): Map<String, Any> {
//        val uid: String = TokenUtil.getToken()
        val map: MutableMap<String, Any> = HashMap(16)
//        if (!TextUtils.isEmpty(uid)) {
//            map["uid"] = uid
//        }
//        map["t"] = System.currentTimeMillis()
//        map["c"] = ApiConfig.PLATFORM_TYPE
//        map["y"] = ApiConfig.CLIENT_TYPE
//        map["v"] = AppUtils.getAppVersionName()
//        map["os"] = DeviceUtils.getModel().toString() + "_" + DeviceUtils.getSDKVersionName() //机型_操作系统版本号
//        map["d"] = DeviceUtils.getUniqueDeviceId() //设备
//        val json: String = paramsBizEncrypt(parameter)
//        map["q"] = json
//        if (isNeedEncrypt()) {
//            map["sign"] = encryptSign(json)
//        } else {
//            //todo
//            map["sign"] = "sign"
//        }
        return map
    }


}