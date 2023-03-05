package com.example.webview.ui

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.webkit.WebViewRenderProcessClient
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.webview.R
import com.example.webview.databinding.ActivityWebViewBinding
import com.example.webview.renderClient.MyWebViewRenderClient

@Route(path = "/ii/ee")
class webViewActivity : AppCompatActivity() {
    private lateinit var databing: ActivityWebViewBinding
//    映射
    @Autowired
    lateinit var url: String
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databing = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(databing.root)
        ARouter.getInstance().inject(this)
        var mm = url
        Log.d("ss",url!!)
        databing.webview.apply {
            canGoBack()
            canGoForward()
            progress
            settings.mixedContentMode  = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE  //混合内容模式，图片加载为http
            settings.javaScriptEnabled
            settings.supportZoom()
            settings.builtInZoomControls = true
            settings.allowFileAccess = false
            loadUrl(mm)
//            webViewRenderProcessClient = MyWebViewRenderClient()
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

}