package com.example.searchactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content")
data class SearchModel(
    @PrimaryKey var id:Int? = null,
    var title: String? = null,
    var link:String? = null,
    var niceDate:String ?=null,
    var shareUser:String?=null,
)