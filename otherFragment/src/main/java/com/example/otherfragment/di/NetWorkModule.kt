package com.example.otherfragment.di

import com.example.otherfragment.remote.api
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
object NetWorkModule {
    @Singleton
    @Provides
    fun provideinfoservice(retrofit: Retrofit): api {
        //直接注入
        return retrofit.create(api::class.java)
    }

}