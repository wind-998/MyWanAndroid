package com.example.newsfragment.mapper

import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.model.ALL
import com.example.newsfragment.model.Info
import com.example.newsfragment.model.info1
//未使用
//进行网络获取数据和使用数据，之间实体的适配和 转换
class Entity2ItemModelMapper :Mapper<info1,NewsEntity> {
    //未使用
    override fun map(input: info1): NewsEntity {
        return NewsEntity(title = input.title, link = input.link, niceDate = input.niceDate, shareUser = input.shareUser)
    }
}