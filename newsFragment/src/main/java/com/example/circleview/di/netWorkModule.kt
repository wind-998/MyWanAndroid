package com.example.circleview.di

import com.example.circleview.remote.api
import com.example.newsfragment.network.Intecepter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object netWorkModule {
    @Singleton
    @Provides
    fun provideinfoservice(retrofit: Retrofit): api {
        //直接注入
        return retrofit.create(api::class.java)
    }

}