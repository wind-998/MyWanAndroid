package com.example.searchactivity.di

import com.example.searchactivity.Respository.Repository
import com.example.searchactivity.Respository.repositoryImpl
import com.example.searchactivity.database.Appdatabase
import com.example.searchactivity.remote.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        return repositoryImpl(api, database,
//            Entity2ItemModelMapper()
        )

    }
}