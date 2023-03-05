package com.example.newsfragment.di

import android.app.Application
import androidx.room.Room
import com.example.newsfragment.db.Appdatabase
import com.example.newsfragment.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideAppDataBase( application: Application): Appdatabase {
        return Room.databaseBuilder(application,Appdatabase::class.java,"news.db").build()
    }

    @Singleton
    @Provides
    fun provideDataBase(database: Appdatabase):NewsDao{
        return database.getdao()
    }
}