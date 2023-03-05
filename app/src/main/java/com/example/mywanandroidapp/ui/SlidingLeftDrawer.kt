package com.example.mywanandroidapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mywanandroidapp.R

class SlidingLeftDrawer:Fragment(R.layout.slidingleftdrawer) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("sliding","show")
    }
}