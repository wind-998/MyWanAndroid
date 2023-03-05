package com.example.happyfragment.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TabEntity(
    var data: List<info>?=null,
    var errorcode: Int?=null,
    var errorMsg: String?=null
) {
}
data class info(
    var articleList: List<String>?=null,
    var author: String?=null,
    var children: List<String>?=null,
    var courseId: Int?=null,
    var cover: String?=null,
    var desc: String?=null,
    var id: Int?=null,                  //获取列表使用
    var lisense: String?=null,
    var lisenseLink: String?=null,
    var name: String?=null,                 //分类
    var order: Long?=null,
    var parentChapterId: Int?=null,
    var type: Int?=null,
    var userControlSetTop: Boolean?=null,
    var visible: Int?=null
)