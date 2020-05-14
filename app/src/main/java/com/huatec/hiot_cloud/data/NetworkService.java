package com.huatec.hiot_cloud.data;


import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * 网络接口请求
 */
public interface NetworkService {
    public static final String BASE_URL = "http://114.67.88.191:8080";

    //retorfit登录
    @POST("/auth/login")
    Observable<ResultBase<LoginResultDTO>> login(@Query("username") String userName, @Query("password") String password,
                                                 @Query("loginCode") String loginCode);

    //retorfit获取信息
    @GET("/user/one")
    Observable<ResultBase<UserBean>> getUserInfo(@Header("Authorization") String authorization);


    //retorfit更改邮箱
    @PUT("/user/email")
    Observable<ResultBase<String>> updateEmail(@Header("Authorization") String authorization, @Query("email") String email);

    //retorfit注册
    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);


}
