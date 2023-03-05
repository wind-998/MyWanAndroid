package com.example.webview.renderClient

import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewRenderProcess
import android.webkit.WebViewRenderProcessClient
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
class MyWebViewRenderClient:WebViewRenderProcessClient() {
    override fun onRenderProcessUnresponsive(p0: WebView, p1: WebViewRenderProcess?) {

    }

    override fun onRenderProcessResponsive(p0: WebView, p1: WebViewRenderProcess?) {
        if (p1 != null) {
            // 停止渲染进程
            p1.terminate()
            // 在新的渲染进程中重新加载页面
            p0?.reload()
        }
    }

}