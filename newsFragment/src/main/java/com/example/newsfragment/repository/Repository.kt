package com.example.newsfragment.repository

import androidx.paging.PagingData
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.model.ALL
import com.example.newsfragment.model.Info
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchnewsList(): Flow<PagingData<NewsEntity>>
}