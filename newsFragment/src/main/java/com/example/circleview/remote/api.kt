package com.example.circleview.remote

import com.example.circleview.entity.banner
import com.example.newsfragment.model.ALL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @GET("banner/json")
    suspend fun getInfo(): banner
}