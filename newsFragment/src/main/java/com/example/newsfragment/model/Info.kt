package com.example.newsfragment.model

import java.util.Spliterators.iterator


//网络获取信息
//data class Info(var id :Int,var name:String,var icon:String) {
//}
data class ALL(var data:Info?=null,
               var errorCode: Int?=null,
               var errorMsg: String?=null
)
data class Info(
    var curPage:Int?=null,
    var datas: List<info1>?= null,
    var offset:Int? = null,
    var over  : Boolean?=null,
    var pageCount: Int?=null,
    var size: Int?=null,
    var total: Int?=null
)
data class info1(
    var adminAdd1: Boolean? = null,
    var apkLink: String? = null,
    var audit: Int?= null,
    var author: String?= null,
    var canEdit: Boolean?= null,
    var chapterId: Int?= null,
    var chapterName: String?= null,
    var collect: Boolean?= null,
    var courseId: Int?= null,
    var desc: String?= null,
    var descMd: String?= null,
    var envelopePic: String?= null,
    var fresh: Boolean?= null,
    var host: String?= null,
    var id: Int?= null,
    var isAdminAdd: Boolean?= null,
    var link: String?= null,
    var niceDate: String?= null,
    var niceShareDate: String?= null,
    var origin: String?= null,
    var prefix: String?= null,
    var projectLink: String?= null,
    var publishTime: Long?= null,
    var realSuperChapterId: Int?= null,
    var route: Boolean?= null,
    var selfVisible: Int?= null,
    var shareDate: Long?= null,
    var shareUser: String?= null,
    var superChapterId: Int?= null,
    var superChapterName: String?= null,
    var tags: List<info3>?= null,
    var title: String?= null,
    var type: Int?= null,
    var userId: Int?= null,
    var visible: Int?= null,
    var zan: Int?= null
)
data class info3(var name: String?=null,
                var url: String?=null
)
