package com.example.newsfragment.db

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsfragment.Entity.KeyEntity
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.model.Info
import dagger.hilt.android.qualifiers.ApplicationContext

//得到数据库，的操作对象
@Database(entities = [NewsEntity::class,KeyEntity::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class Appdatabase: RoomDatabase() {
    abstract fun getdao():NewsDao
    abstract fun getKeydao():KeyDao
}