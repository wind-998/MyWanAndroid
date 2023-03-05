package com.example.happyfragment.repository

import androidx.paging.PagingData
import com.example.happyfragment.Entity.HappyEntity
import com.example.happyfragment.Entity.TabEntity
import com.example.happyfragment.Entity.info
import com.example.happyfragment.Entity.info2
import com.example.happyfragment.model.happymodel
import com.example.happyfragment.model.tabmodel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchHappyList(uid:Int): Flow<PagingData<happymodel>>
    fun fetchTabList(): Flow<List<tabmodel>>
}