package com.example.searchactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "contentkey")
data class keyModel(@PrimaryKey
                    var name:String,var page: Int? = 0) {
}