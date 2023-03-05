package com.example.newsfragment.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
//用来记录自定义本地分页，
@Entity(tableName = "keys")
//一种key只会有一个值
data class KeyEntity(@PrimaryKey var name:String,var page: Int? = 0) {
}