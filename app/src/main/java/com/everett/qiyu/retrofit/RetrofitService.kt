package com.everett.qiyu.retrofit


import com.everett.qiyu.bean.BannerData
import com.everett.qiyu.bean.JsonData
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    /**
     *登录
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginSystem(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<JsonData>

    /**
     * 注册
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerSystem(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassowrd: String
    ): Call<JsonData>




    /**
     * 获取首页Banner
     */
    @GET("/banner/json")
    fun getBanner():Call<BannerData>

}