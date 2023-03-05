package com.example.otherfragment.viewmodel

import com.example.otherfragment.remote.api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//用来给viewmodel初始化数据
class Factory constructor(var api: api):UserViewModel(api) {
}