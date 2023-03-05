package com.example.happyfragment.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.happyfragment.adapter.happyAdapter
import com.example.happyfragment.adapter.tabadapter
import com.example.happyfragment.databinding.FragmentHappyBinding
import com.example.happyfragment.hviewmodel.HappyViewModel
import com.example.happyfragment.model.tabmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class happyFragment : androidx.fragment.app.Fragment() {
    private  lateinit var databind :FragmentHappyBinding
    private val viewmodel:HappyViewModel by viewModels()
    private var tabadapter: tabadapter? = null
    private var happyAdapter: happyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databind = FragmentHappyBinding.inflate(inflater)

        CoroutineScope(Dispatchers.Main).launch{
            viewmodel.n.collect {//给tab填充数据
                viewmodel.Tabdata = MutableLiveData<List<tabmodel>>(it)
            }

            var onetime = true

            //若第一次为null则不添加
            viewmodel.Tabdata!!.observe(viewLifecycleOwner) {
//            tab数据发生变化时，进行配置适配器
                if(onetime){
                    viewmodel.flag.value = viewmodel.Tabdata?.value!!.first().id
                    onetime =false
                }
                tabadapter = viewmodel.Tabdata?.value?.let { it1 -> tabadapter(it1,viewmodel) }
                databind.recycler2.adapter = tabadapter
            }
        }

        viewmodel.flag.observe(viewLifecycleOwner) {
//            根据点击id的变换，刷新
            happyAdapter?.refresh()       //刷新适配器

            viewmodel.data2(viewmodel.flag.value!!).observe(viewLifecycleOwner) {
                //数据发生改变，赋值
                var thisflag = viewmodel.flag.value
                if(thisflag == viewmodel.flag.value)
                {
                    happyAdapter!!.submitData(lifecycle,it)
                }
            }
        }
        happyAdapter = happyAdapter()
        databind.recycler3.adapter = happyAdapter


        lifecycleScope.launchWhenCreated {
            happyAdapter!!.loadStateFlow.collectLatest { state ->
//                判断状态是否需要进行下一次加载或结束,数据自动完成
                databind.refresh3.isRefreshing = state.refresh is LoadState.Loading
            }
        }
        databind.refresh3.setOnRefreshListener {
            //若不停止，则将一直刷新
            Log.d("刷新","show")
            happyAdapter!!.refresh()
        }
        return databind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = happyFragment().apply {
        }
    }
}