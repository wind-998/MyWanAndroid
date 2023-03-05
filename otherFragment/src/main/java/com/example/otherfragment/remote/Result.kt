package com.example.otherfragment.remote

data class LoginResult(
    var data: pp,
    var errorCode: Int,
    var errorMsg: String
)
data class pp(
    var admin: Boolean,
    var chapterTops: List<String>,
    var coinCount: Int,
    var collectIds: List<String>,
    var email: String,
    var icon: String,
    var id: Long,
    var nickname: String,
    var password: String,
    var publicName: String,
    var token: String,
    var type: Int,
    var username: String
)