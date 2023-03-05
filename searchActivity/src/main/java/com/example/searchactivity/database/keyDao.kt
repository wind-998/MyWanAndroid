package com.example.searchactivity.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.searchactivity.model.keyModel

@Dao
interface keyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertkey(key: keyModel)

    //    数据库内获取数据，并进行分页,获取数据库的分页关键字
    @Query("select * from contentkey where name = :name")
    suspend fun getkey(name:String): keyModel

    @Query("delete from contentkey where name = :name")
    suspend fun clearkey(name: String)
}