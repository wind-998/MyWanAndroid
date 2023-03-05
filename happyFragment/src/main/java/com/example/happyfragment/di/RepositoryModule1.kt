package com.example.happyfragment.di

import com.example.happyfragment.db.Appdatabase
import com.example.happyfragment.remote.api
import com.example.happyfragment.repository.NewsRepositoryImpl
import com.example.happyfragment.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule1 {
    @Singleton
    @Provides
    fun provideRepository1(
        api: api,
        database: Appdatabase
    ): Repository {
        return NewsRepositoryImpl(api, database,
//            Entity2ItemModelMapper()
        )

    }
}