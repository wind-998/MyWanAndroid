package com.example.newsfragment.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfragment.Entity.KeyEntity
import com.example.newsfragment.Entity.NewsEntity

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertkey(key: KeyEntity)

    //    数据库内获取数据，并进行分页,获取数据库的分页关键字
    @Query("select * from keys where name = :name")
    suspend fun getkey(name:String): KeyEntity

    @Query("delete from keys where name = :name")
    suspend fun clearkey(name: String)
}