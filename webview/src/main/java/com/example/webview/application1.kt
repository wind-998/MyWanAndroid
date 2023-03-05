package com.example.webview

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class application1:Application() {
    override fun onCreate() {
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();//调试必须打开
        ARouter.init(this)
        super.onCreate()
    }
}