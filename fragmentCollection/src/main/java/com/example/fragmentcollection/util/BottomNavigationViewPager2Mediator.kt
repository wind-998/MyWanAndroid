package com.example.fragmentcollection.util

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * BottomNavigationView和ViewPager2联动的中介者,可复用
 */
class BottomNavigationViewPager2Mediator(
    private val navigation: BottomNavigationView,
    private val pager: ViewPager2,
    private val config: ((BottomNavigationView, ViewPager2) -> Unit)? = null
) {

    private var isPageScroll: Boolean = false

    /**
     * 存储BottomNavigationView的menu的item和它对应的index索引位置
     */
    private val map = mutableMapOf<MenuItem, Int>()

    init {
        // item与其index存储
        navigation.menu.forEachIndexed { index, item ->
            map[item] = index
        }
    }

    fun attach() {
        config?.invoke(navigation, pager)

        // ViewPager点击，绑定
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isPageScroll = true
                navigation.selectedItemId = navigation.menu.getItem(position).itemId
            }
        })

        //底部tab点击
        navigation.setOnItemSelectedListener {
//            判断是否处于滑动状态
            if (isPageScroll) {
                isPageScroll = false
                return@setOnItemSelectedListener true
            }
            pager.setCurrentItem(map[it]!!, false)
            true
        }
    }
}