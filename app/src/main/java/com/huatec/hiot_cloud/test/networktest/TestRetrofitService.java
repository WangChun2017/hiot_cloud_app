package com.huatec.hiot_cloud.test.networktest;

import com.huatec.hiot_cloud.data.bean.UserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Retrofit Service 接口
 */
public interface TestRetrofitService {
//  public static  final String  baseUrl= "https://www.baidu.com";
    public static final String baseUrl = "http://114.67.88.191:8080/";

    //retorfit访问百度
    @GET("/")
    Call<ResponseBody> test();

    //retorfit登录
    @POST("/auth/login")
    Call<ResponseBody> login(@Query("username") String userName, @Query("password") String password,
                             @Query("loginCode") String loginCode);

    //retorfit获取信息
    @GET("/user/one")
    Call<ResponseBody> getUserInfo(@Header("Authorization")String authorization);

    @GET("/user/one")
    Call<ResultBase<UserBean>> getUserInfo2(@Header("Authorization")String authorization);

    //retorfit更改邮箱
    @PUT("/user/email")
    Call<ResponseBody> updateEmail(@Header("Authorization")String authorization,@Query("email")String email);

    //retorfit注册
    @POST("/user/register")
    Call<ResponseBody> register(@Body UserBean userBean);

}
