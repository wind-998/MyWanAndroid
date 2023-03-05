package com.example.happyfragment.repository

import android.util.Log
import androidx.paging.*
import com.example.happyfragment.db.Appdatabase
import com.example.happyfragment.model.happymodel
import com.example.happyfragment.model.tabmodel
import com.example.happyfragment.remote.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class  NewsRepositoryImpl  constructor(private val api : api,
                                       private val database: Appdatabase,
//                          private val mapper2item: Mapper<ALL,NewsEntity>
):Repository {
//    进行分页加载数据，首先从网络加载到数据库，然后冲数据库加载出来并返回流
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchHappyList(uid:Int): Flow<PagingData<happymodel>> {
//    , prefetchDistance = 1 //距离最后一个item多少时，进行加载，默认为pagesize
        return Pager(config = PagingConfig(pageSize = 8 , prefetchDistance = 1, initialLoadSize = 16, enablePlaceholders = true),
            remoteMediator = com.example.happyfragment.remote.contentRemoteMediator(uid,api,database) // 网络加载数据到数据库
        ){
            database.getdao().getHappy()      //数据库加载数据出来使用
        }.flow.flowOn(Dispatchers.IO)
//            .map {
                //对流进行格式转换为所需要的格式,,数据库格式转化为model格式
//                it.map {
//                    mapper2item.map(it)
//                }
//            }
    }

    override fun fetchTabList(): Flow<List<tabmodel>> {
        return flow {
            try {
                val Keydao = database.getTabdao()
                // 查询数据库是否存在，如果不存在请求网络
                var infoModel = Keydao.gettabs()
                if (infoModel.size == 0) {
                    // 网络请求

                    val netWorkPokemonInfo = api.getInfo1()
                    var e = netWorkPokemonInfo.data
//            二层转化为一层
                    val ll :MutableList<tabmodel> = mutableListOf()
                    for (input in e!!){
                        ll.add(tabmodel(id =input.id,
                            name = input.name
                        ))
                    }
                    infoModel = ll
                    // 将网路请求的数据，换转成的数据库的 model，之后插入数据库
//                    infoModel = netWorkPokemonInfo.data!!
                    // 插入更新数据库
                    Keydao.inserttabs(infoModel)
                }

                // 发射转换后的数据
                emit(infoModel)
            } catch (e: Exception) {
//                emit(PokemonResult.Failure(e.cause))
                Log.d("错误",e.toString())
            }
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
    }


}