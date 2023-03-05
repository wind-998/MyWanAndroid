package com.example.fragmentcollection.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.happyfragment.ui.happyFragment
import com.example.newsfragment.ui.newsFragment
import com.example.otherfragment.ui.otherFragment
import com.example.studyfragment.ui.studyFragment

class ViewPagerAdapter(private var fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            //返回实例
            0 -> newsFragment()
            1 -> happyFragment()
            else -> otherFragment()
        }
    }
}