package com.example.fragmentcollection.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.fragmentcollection.R
import com.example.fragmentcollection.adapter.ViewPagerAdapter
import com.example.fragmentcollection.databinding.MainfragmentBinding
import com.example.fragmentcollection.util.BottomNavigationViewPager2Mediator
import com.example.happyfragment.ui.happyFragment

class mainFragment:Fragment() {
    private lateinit var  databind : MainfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databind = MainfragmentBinding.inflate(LayoutInflater.from(context),container,false)
        databind.apply {
            pager2.adapter = ViewPagerAdapter(this@mainFragment)
            pager2.offscreenPageLimit = 3
            BottomNavigationViewPager2Mediator(bottomNavigationView,pager2).attach()
//            Toast.makeText(context,"collection", Toast.LENGTH_SHORT).show()
            databind.linear2.setOnClickListener{
                ARouter.getInstance().build("/kk/dd")
                    .navigation()
            }
        }
        return databind.root
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            mainFragment().apply {
            }
    }
}