package com.example.happyfragment.remote

import com.example.happyfragment.Entity.HappyEntity
import com.example.happyfragment.Entity.TabEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    //    请求页面的数据，指定获得多少页面，，，返回的数据类型是Infp类型，，Info类型包括数据curpage,list<info>
    @GET("project/list/{page}/json")
    suspend fun getInfo(
        @Path("page")since:Int,
        @Query("cid") cid:Int,
        @Query("page_size")pagesize: Int
    ): HappyEntity
//    获取导航列表

//    https://www.wanandroid.com/
    @GET("project/tree/json")
    suspend fun getInfo1(): TabEntity
}