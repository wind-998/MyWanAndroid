package com.example.happyfragment.hviewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.happyfragment.model.happymodel
import com.example.happyfragment.model.tabmodel
import com.example.happyfragment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HappyViewModel  @Inject constructor(private val repository: Repository):ViewModel() {
    var flag : MutableLiveData<Int> = MutableLiveData<Int>(294)

    var n = repository
        .fetchTabList()
    var Tabdata:LiveData<List<tabmodel>>? =null
//        repository
//            .fetchTabList()
//            .asLiveData()
//    val data : LiveData<PagingData<happymodel>>? =
//        flag?.value?.let {
//            repository
//                .fetchHappyList(it)
//                . cachedIn (viewModelScope)
//                . asLiveData()
//        }

    fun data2(index:Int):LiveData<PagingData<happymodel>>{
        return repository
                .fetchHappyList(index)
                . cachedIn (viewModelScope)
                . asLiveData()
    }


}