package com.example.searchactivity.di

import com.example.searchactivity.remote.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object netWork {
    @Singleton
    @Provides
    fun provideinfoservice(retrofit: Retrofit): api {
        //直接注入
        return retrofit.create(api::class.java)
    }
}
