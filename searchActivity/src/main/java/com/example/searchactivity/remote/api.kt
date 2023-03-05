package com.example.searchactivity.remote

import com.example.searchactivity.Entity.SearchEntity
import retrofit2.http.*

interface api {
    //    请求页面的数据，指定获得多少页面，，，返回的数据类型是Infp类型，，Info类型包括数据curpage,list<info>
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun getInfo(
        @Path("page")page:Int,
        @Query("page_size")pagesize: Int,
        @Field("k") content:String
    ): SearchEntity
}