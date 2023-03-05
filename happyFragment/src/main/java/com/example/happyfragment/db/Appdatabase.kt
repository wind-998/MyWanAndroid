package com.example.happyfragment.db

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.happyfragment.Entity.*
import com.example.happyfragment.model.happymodel
import com.example.happyfragment.model.tabmodel

//得到数据库，的操作对象
@Database(entities = [happymodel::class, tabmodel::class,KeyEntity::class], version = 2, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class Appdatabase: RoomDatabase() {
    abstract fun getdao():HappyDao
    abstract fun getTabdao():TabDao
    abstract fun getKeydao():KeyDao
}