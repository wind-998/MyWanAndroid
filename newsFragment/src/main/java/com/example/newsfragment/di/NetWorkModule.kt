package com.example.newsfragment.di

import com.example.newsfragment.network.Intecepter
import com.example.newsfragment.remote.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//网络加载模块
@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Intecepter()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build( )
    }
    @Singleton
    @Provides
    fun provideinfoservice(retrofit: Retrofit): api {
        //直接注入
        return retrofit.create(api::class.java)
    }

}