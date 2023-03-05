package com.example.happyfragment.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.happyfragment.Entity.info
import com.example.happyfragment.Entity.info2
import com.example.happyfragment.model.happymodel

@Dao
interface HappyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHappy(info: List<happymodel>)

    //    数据库内获取数据，并进行分页,获取数据库的分页关键字
    @Query("select * from Happy")
    fun getHappy(): PagingSource<Int,happymodel>

    @Query("delete from Happy")
    suspend fun clearHappy()
}