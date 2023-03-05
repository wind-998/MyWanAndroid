package com.example.newsfragment.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MyRetrofit {
    private val client: OkHttpClient = OkHttpClient.Builder() //builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10, TimeUnit.SECONDS) // 读取超时
        .writeTimeout(10, TimeUnit.SECONDS) //写 超时
//    .addInterceptor(LoggingInterceptor())
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://www.fastmock.site/mock/e8032449e3afae6058bc152fc6b9679b/info/")
        //自动转化为对象数据
        .addConverterFactory(GsonConverterFactory.create()) //数据转换适配器
        .build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}