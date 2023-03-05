package com.example.happyfragment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tab")
data class tabmodel(
    @PrimaryKey var id:Int?=null,
    var name:String?=null
) {
}