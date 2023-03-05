package com.example.searchactivity.remote

import androidx.paging.*
import androidx.room.withTransaction
import com.example.searchactivity.database.Appdatabase
import com.example.searchactivity.model.SearchModel
import com.example.searchactivity.model.keyModel
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class contentRemoteMediator @Inject constructor(var content:String, private val api: api, private val database: Appdatabase)
    : RemoteMediator<Int, SearchModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchModel>
    ): MediatorResult {
        try {
            val remoteKeys = database.getKeydao()
            var pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH ->
                {
                    null
                }
                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                // 加载更多时触发
                LoadType.APPEND -> {
                    //自定义
                    var key = database.withTransaction {
                        remoteKeys.getkey("content")
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
            if(content==null) return MediatorResult.Success(endOfPaginationReached = true)

            //第二步，请求网络分页数据,多页数据，每页数据，多条item
            val page = pageKey ?: 0
            val result = api.getInfo(
//                page * state.config.pageSize,
                page,
                state.config.pageSize,
                content
            )

            if (result == null) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }


            var e = result.data!!.datas
//            二层转化为一层
            val ll :MutableList<SearchModel> = mutableListOf()
            for (input in e!!){
                ll.add(SearchModel(title = input.title,
                    link = input.link,
                    niceDate = input.niceDate,
                    shareUser = input.shareUser))
            }

            val endOfPaginationReached = false
            //第三步，插入数据库。

            val contentdao = database.getdao()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeys.clearkey("content")
                    contentdao.clearContent()
                }
                val nextpage = if (endOfPaginationReached) null else page + 1
                //用来保存每次获取数据的最后一页的实体
                val keyentity = keyModel(
                    name = "content",
                    page = nextpage
                )
                //若有冲突会进行替换
                remoteKeys.insertkey(keyentity)
                contentdao.insertContent(ll)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }
}