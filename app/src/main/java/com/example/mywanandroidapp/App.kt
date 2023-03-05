package com.example.mywanandroidapp

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.androidisland.vita.startVita
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class APP:Application() {
    override fun onCreate() {
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();//调试必须打开
        ARouter.init(this)
        startVita()
        super.onCreate()
    }
    init {
        Log.d("aa","app")
    }
}