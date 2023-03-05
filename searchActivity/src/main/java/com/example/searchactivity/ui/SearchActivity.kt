package com.example.searchactivity.ui

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.searchactivity.adapter.pagingadapter
import com.example.searchactivity.databinding.SearchfragmentBinding
import com.example.searchactivity.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Route(path = "/kk/dd")
@AndroidEntryPoint
class SearchActivity:AppCompatActivity() {
    private val databinding:SearchfragmentBinding by lazy {
        SearchfragmentBinding.inflate(layoutInflater)
    }
    private val viewmodel:SearchViewModel by viewModels()
    private val adapter:pagingadapter by lazy {
        pagingadapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(databinding.root)
        databinding.searchrecycler.adapter = adapter
        databinding.mmviewmodel = viewmodel
        databinding.lifecycleOwner = this

        databinding.searchView22.setOnClickListener{
            clickSearch()
        }
    }
    fun clickSearch(){
        Toast.makeText(this,"开始查找",Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.Main).launch {
            adapter.refresh()
            var cataglory = viewmodel.searchcontent.value
            viewmodel.searchData().observe(this@SearchActivity) {
                if(cataglory == viewmodel.searchcontent.value)
                    adapter.submitData(lifecycle,it)
                }
            }
    }
}