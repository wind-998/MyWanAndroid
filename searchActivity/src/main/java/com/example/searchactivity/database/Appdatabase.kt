package com.example.searchactivity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchactivity.model.SearchModel
import com.example.searchactivity.model.keyModel

//得到数据库，的操作对象
@Database(entities = [keyModel::class,SearchModel::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class Appdatabase: RoomDatabase() {
    abstract fun getdao():contentDao
    abstract fun getKeydao():keyDao
}