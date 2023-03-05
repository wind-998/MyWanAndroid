package com.example.newsfragment

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class application:Application() {
    override fun onCreate() {
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();//调试必须打开
        ARouter.init(this)
        super.onCreate()
    }
}