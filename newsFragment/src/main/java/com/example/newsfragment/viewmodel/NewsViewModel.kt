package com.example.newsfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.repository.Repository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository
) : ViewModel () {
//    @Inject lateinit  var repository: Repository
    val data : LiveData<PagingData<NewsEntity>> =
        repository
            .fetchnewsList()
            . cachedIn (viewModelScope)
            . asLiveData()
//    val data = Pager(PagingConfig(pageSize = 6)) {
//        MyPagingSource()
//    }.map {
//    Entity2ItemModelMapper().map(it)
//}.flow.cachedIn(viewModelScope)
}