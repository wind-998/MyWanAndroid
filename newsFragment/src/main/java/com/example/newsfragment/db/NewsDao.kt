package com.example.newsfragment.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.model.ALL
import com.example.newsfragment.model.Info
import com.example.newsfragment.model.info1

//对数据库进行操作的方法
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsList:List<NewsEntity>)

    //    数据库内获取数据，并进行分页
    @Query("select * from news")
    fun getNews(): PagingSource<Int, NewsEntity>

    @Query("delete from news")
    fun clearnews()
}