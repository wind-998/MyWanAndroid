package com.example.otherfragment.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @POST("user/login")
    @FormUrlEncoded
    suspend fun getCheck(@Field("username")name:String?,@Field("password")password:String?) :LoginResult

    @POST("user/register")
    @FormUrlEncoded
    suspend fun getregister(@Field("username")name:String?,@Field("password")password:String?,
                            @Field("repassword")repassword:String?):LoginResult
}