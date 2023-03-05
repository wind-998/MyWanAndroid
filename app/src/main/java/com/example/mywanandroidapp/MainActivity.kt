package com.example.mywanandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.MotionEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.fragmentcollection.ui.mainFragment
import com.example.mywanandroidapp.databinding.ActivityMainBinding
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import dagger.hilt.android.AndroidEntryPoint

//主要活动，处理抽屉布局和主界面的填充
@Route(path = "/main/activi")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val databing by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    //进行抽屉布局
    lateinit var slidingRootNav: SlidingRootNav
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(databing.root)
//        setSupportActionBar(findViewById(R.id.maintool))
//        slidingRootNav = SlidingRootNavBuilder(this)
//        .withToolbarMenuToggle(databing.maintool)
//            .withMenuOpened(false)
//            .withContentClickableWhenMenuOpened(false)
//            .withSavedState(savedInstanceState)
//            .withMenuLayout(R.layout.slidingleftdrawer)
//            .inject()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, mainFragment())
            .commit()
    }
//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        slidingRootNav!!.closeMenu() //
//        return super.dispatchTouchEvent(ev)
//    }
}