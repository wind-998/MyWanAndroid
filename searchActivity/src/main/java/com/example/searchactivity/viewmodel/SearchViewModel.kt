package com.example.searchactivity.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.searchactivity.Respository.Repository
import com.example.searchactivity.Respository.repositoryImpl
import com.example.searchactivity.model.SearchModel
import com.example.searchactivity.remote.api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    val searchcontent:MutableLiveData<String> = MutableLiveData("")
    fun searchData() : LiveData<PagingData<SearchModel>> {
        return repository.fetchContentList(searchcontent.value.toString()).cachedIn (viewModelScope).asLiveData()
    }
}