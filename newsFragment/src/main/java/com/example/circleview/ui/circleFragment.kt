package com.example.circleview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.viewpager2.widget.ViewPager2
import com.example.circleview.adapter.circlePager
import com.example.circleview.entity.info
import com.example.circleview.viewmodel.CircleViewModel
import com.example.newsfragment.databinding.CirclefragmentBinding
import com.example.newsfragment.ui.newsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class circleFragment:Fragment() {
    private lateinit var databing:CirclefragmentBinding
    private val viewmodel:CircleViewModel by viewModels()
    var mTimer = Timer()
    var mCurrentPosition:Int = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databing = CirclefragmentBinding.inflate(LayoutInflater.from(context))
        var adapter:circlePager
        var imagelist :List<info>
        CoroutineScope(Dispatchers.Main).launch {
            imagelist = viewmodel.imagePath()!!
            adapter = circlePager(imagelist)
            databing.pager8.adapter = adapter
        }
        databing.pager8.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mCurrentPosition = position
            }
        })
        mTimer.schedule(object : TimerTask() {
            @SuppressLint("RestrictedApi")
            override fun run() {
                mCurrentPosition++
                if (mCurrentPosition >= 3) {
                    mCurrentPosition = 0
                }
                databing.pager8.post {
                    databing.pager8.setCurrentItem(mCurrentPosition, true)
                }
            }
        }, 3000, 3000)

        return databing.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer.cancel()
    }
    companion object {
        private var instance:circleFragment?=null
        @JvmStatic
        fun newInstance():circleFragment{
            if(instance == null){
                instance = circleFragment()
            }
                return instance as circleFragment
        }
    }
}