package com.example.circleview.entity

data class banner(var data:List<info>?=null, var errcode:Int?=null,var errorMsg:String?=null) {
}
data class info(var desc:String?=null,
                var id:Int?=null,
                var imagePath:String?=null,
                var isVisible:Int?=null,
                var order:Int?=null,
                var title:String?=null,
                var type:Int?=null,
                var url:String?=null)