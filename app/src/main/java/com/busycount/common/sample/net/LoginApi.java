package com.busycount.common.sample.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface LoginApi {

    /**
     * 登录接口
     */
    @POST("")
    Observable<Object> loginPwd(@QueryMap Map<String, Object> queryMap, @Body RequestBody requestBody);

}
