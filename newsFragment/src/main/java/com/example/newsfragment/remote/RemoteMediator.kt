package com.example.newsfragment.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.room.Insert
import androidx.room.withTransaction
import com.example.newsfragment.Entity.KeyEntity
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.db.Appdatabase
import com.example.newsfragment.model.ALL
import com.example.newsfragment.model.Info
import com.example.newsfragment.model.info1
import java.util.Arrays.toString
import javax.inject.Inject

//从网络加载数据，存入数据库
@OptIn(ExperimentalPagingApi::class)
class RemoteMediator @Inject  constructor(private val api: api, private val database: Appdatabase)
    : androidx.paging.RemoteMediator<Int, NewsEntity>() {//从网络获取的
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsEntity>   //pagineState内部是一个key和value数据的一个集合
    ): MediatorResult {
        try {
            Log.d("remoteMediator","jiazia")
            //第一步，判断 LoadType，根据LoadType计算page（当前页）

            val remoteKeys = database.getKeydao()
//            是否上次加载过
            var pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH ->
                {
                    Log.d("更多", "1")
                    null
                }
                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> {
                    Log.d("更多", "2")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                // 加载更多时触发
                LoadType.APPEND -> {
                    //该方式适用于服务器端的数据实体内包含页面号码
//                    val lastItem: NewsEntity =
//                        state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
//                    Log.d("更多", "3")
//                    //得到上次加载的最后一个实体的页面号码
//                    lastItem.page
                    //自定义
                    var key = database.withTransaction {
                        remoteKeys.getkey("news")
                    }
                    if (key == null || key.page == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    key.page
                }
            }

            // 无网络，加载本地数据
//            if (!AppHelper.mContext.isConnectedNetwork()){
//                return MediatorResult.Success(endOfPaginationReached = true)
//            }

            //第二步，请求网络分页数据,多页数据，每页数据，多条item
            val page = pageKey ?: 0
            val result = api.getInfo(
//                page * state.config.pageSize,
                page,
                state.config.pageSize
            )

            if (result == null) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }


            var e = result.data!!.datas
//            二层转化为一层
            val ll :MutableList<NewsEntity> = mutableListOf()
                for (input in e!!){
                    ll.add(NewsEntity(title = input.title,
                        link = input.link,
                        niceDate = input.niceDate,
                        shareUser = input.shareUser,
                        page = page))
                }

            val endOfPaginationReached = false
            //第三步，插入数据库。

            val newsdao = database.getdao()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeys.clearkey("news")
                    newsdao.clearnews()
                }
                val nextpage = if (endOfPaginationReached) null else page + 1
                //用来保存每次获取数据的最后一页的实体
                val keyentity = KeyEntity(
                    name = "news",
                    page = nextpage
                )
                //若有冲突会进行替换
                remoteKeys.insertkey(keyentity)
                newsdao.insertNews(ll)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

}
