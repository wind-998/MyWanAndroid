package com.example.newsfragment.di

import com.example.newsfragment.db.Appdatabase
import com.example.newsfragment.mapper.Entity2ItemModelMapper
import com.example.newsfragment.remote.api
import com.example.newsfragment.repository.NewsRepositoryImpl
import com.example.newsfragment.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        api: api,
        database: Appdatabase
    ): Repository {
        return NewsRepositoryImpl(api, database,
//            Entity2ItemModelMapper()
        )

    }
}