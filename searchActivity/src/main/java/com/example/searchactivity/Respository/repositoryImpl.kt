package com.example.searchactivity.Respository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.searchactivity.database.Appdatabase
import com.example.searchactivity.model.SearchModel
import com.example.searchactivity.remote.api
import com.example.searchactivity.remote.contentRemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class repositoryImpl(private val api : api,
                     private val database: Appdatabase,):Repository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchContentList(content: String): Flow<PagingData<SearchModel>> {
        return Pager(config = PagingConfig(pageSize = 8 , prefetchDistance = 1, initialLoadSize = 16, enablePlaceholders = true),
            remoteMediator = contentRemoteMediator(content,api,database) // 网络加载数据到数据库
        ){
            database.getdao().getContent()      //数据库加载数据出来使用
        }.flow.flowOn(Dispatchers.IO)
    }
}