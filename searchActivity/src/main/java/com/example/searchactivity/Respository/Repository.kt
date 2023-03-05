package com.example.searchactivity.Respository

import androidx.paging.PagingData
import com.example.searchactivity.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchContentList(content:String): Flow<PagingData<SearchModel>>
}