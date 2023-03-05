package com.example.searchactivity.di

import android.app.Application
import androidx.room.Room
import com.example.searchactivity.database.Appdatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDataBase( application: Application): Appdatabase {
        return Room.databaseBuilder(application,Appdatabase::class.java,"ji.db").build()
    }

}