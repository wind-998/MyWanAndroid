package com.example.newsfragment.repository

import android.util.Log
import androidx.paging.*
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.db.Appdatabase
import com.example.newsfragment.mapper.Mapper
import com.example.newsfragment.model.ALL
import com.example.newsfragment.model.Info
import com.example.newsfragment.remote.api
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class  NewsRepositoryImpl  constructor(private val api : api,
                          private val database: Appdatabase,
//                          private val mapper2item: Mapper<ALL,NewsEntity>
):Repository {
//    进行分页加载数据，首先从网络加载到数据库，然后冲数据库加载出来并返回流
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchnewsList(): Flow<PagingData<NewsEntity>> {
//    , prefetchDistance = 1 //距离最后一个item多少时，进行加载，默认为pagesize
        return Pager(config = PagingConfig(pageSize = 8 , prefetchDistance = 1, initialLoadSize = 16, enablePlaceholders = true),
            remoteMediator = com.example.newsfragment.remote.RemoteMediator(api,database) // 网络加载数据到数据库
        ){
            database.getdao().getNews()       //数据库加载数据出来使用
        }.flow.flowOn(Dispatchers.IO)
//            .map {
                //对流进行格式转换为所需要的格式,,数据库格式转化为model格式
//                it.map {
//                    mapper2item.map(it)
//                }
//            }
    }


}