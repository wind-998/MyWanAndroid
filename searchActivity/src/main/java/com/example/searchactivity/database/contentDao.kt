package com.example.searchactivity.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.searchactivity.model.SearchModel

@Dao
interface contentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(info: List<SearchModel>)

    //    数据库内获取数据，并进行分页,获取数据库的分页关键字
    @Query("select * from content")
    fun getContent(): PagingSource<Int, SearchModel>

    @Query("delete from content")
    suspend fun clearContent()
}
