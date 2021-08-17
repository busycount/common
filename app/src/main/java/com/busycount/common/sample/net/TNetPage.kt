package com.busycount.common.sample.net

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author : thalys_ch
 * Date : 2021/08/12
 * Describe :
 **/
class TNetPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val map = mutableMapOf<String, Any>()
        map["phone"] = "phone"
        map["password"] = "password"

//        NetClient.create(LoginApi::class.java).loginPwd(map,map)

    }
}