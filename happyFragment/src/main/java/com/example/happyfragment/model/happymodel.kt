package com.example.happyfragment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Happy")
data class happymodel(
    @PrimaryKey var id:Int? = null,
    var title: String? = null,
    var link:String? = null,
    var niceDate:String ?=null,
    var shareUser:String?=null,
) {
}