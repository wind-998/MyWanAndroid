package com.example.newsfragment.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//数据库对应的数据
@Entity(tableName = "news")
data class NewsEntity(@PrimaryKey var id:Int? = null,
                      var title: String? = null,
                      var link:String? = null,
                      var niceDate:String ?=null,
                      var shareUser:String?=null,
                      var page:Int? = null) {
}