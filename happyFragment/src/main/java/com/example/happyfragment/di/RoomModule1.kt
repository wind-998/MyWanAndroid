package com.example.happyfragment.di

import android.app.Application
import androidx.room.Room
import com.example.happyfragment.db.Appdatabase
import com.example.happyfragment.db.HappyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule1 {
    @Singleton
    @Provides
    fun provideAppDataBase( application: Application): Appdatabase {
        return Room.databaseBuilder(application,Appdatabase::class.java,"jj.db").build()
    }

    @Singleton
    @Provides
    fun provideDataBase(database: Appdatabase): HappyDao {
        return database.getdao()
    }
}