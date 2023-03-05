package com.example.happyfragment.di

import com.example.happyfragment.network.Intecepter
import com.example.happyfragment.remote.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//网络加载模块
@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule1 {
//    @Singleton
//    @Provides
//    fun provideHttpClient1(): OkHttpClient {
//        return OkHttpClient.Builder().addInterceptor(Intecepter()).build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit1(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl("https://www.wanandroid.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build( )
//    }
    @Singleton
    @Provides
    fun provideinfoservice1(retrofit: Retrofit): api {
        //直接注入
        return retrofit.create(api::class.java)
    }

}