package com.example.circleview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circleview.entity.info
import com.example.circleview.remote.api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CircleViewModel @Inject constructor(private val api: api):ViewModel() {
    suspend fun imagePath(): List<info>? {
        return api.getInfo().data
    }

}