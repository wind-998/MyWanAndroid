package com.example.newsfragment.remote

import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.model.ALL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    //    请求页面的数据，指定获得多少页面，，，返回的数据类型是Infp类型，，Info类型包括数据curpage,list<info>
    @GET("article/list/{page}/json")
    suspend fun getInfo(
        @Path("page")since:Int,
        @Query("page_size")pagesize: Int
    ): ALL
}