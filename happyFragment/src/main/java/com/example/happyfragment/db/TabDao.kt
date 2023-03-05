package com.example.happyfragment.db

import androidx.room.*
import com.example.happyfragment.Entity.TabEntity
import com.example.happyfragment.Entity.info
import com.example.happyfragment.model.tabmodel
import com.google.android.material.tabs.TabLayout.Tab

//对数据库进行操作的方法
@Dao
interface TabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttabs(tablist: List<tabmodel>)

    @Query("select * from Tab")
    fun gettabs(): List<tabmodel>

    @Query("delete from Tab")
    fun cleartab()
}