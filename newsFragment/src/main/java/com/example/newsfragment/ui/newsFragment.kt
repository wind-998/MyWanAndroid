package com.example.newsfragment.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.example.circleview.ui.circleFragment
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.R
import com.example.newsfragment.adapter.FooterAdapter
import com.example.newsfragment.adapter.Newsadapter
import com.example.newsfragment.databinding.FragmentNewsBinding
import com.example.newsfragment.viewmodel.NewsViewModel
import com.scwang.smart.refresh.header.ClassicsHeader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class newsFragment : Fragment() {
    private lateinit var databind :FragmentNewsBinding
    private val viewmodel : NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databind = FragmentNewsBinding.inflate(inflater)
        var adapter = Newsadapter(requireContext())

        //        观察数据变化，进行提交数据
        viewmodel.data.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
//            导致下拉不成功
//            databind.refresh1.isEnabled = false
        }
        databind.recycler1.adapter = adapter.withLoadStateFooter(FooterAdapter(adapter,requireContext()))

        adapter.setcall(object :Newsadapter.callback{
            override fun ck(url: String) {
//                Toast.makeText(context,url,Toast.LENGTH_SHORT).show()
//                动画
                var compat = ActivityOptionsCompat.
                makeScaleUpAnimation(databind.root, databind.root.getWidth() / 2, databind.root.getHeight() / 2, 0, 0)
                ARouter.getInstance().build("/ii/ee")
                    .withOptionsCompat(compat)
                    .withString("url", url)
                    .navigation()
//                startActivity(Intent(context,webViewActivity::class.java))
            }

        })
        databind.refresh1.setOnRefreshListener {
            //若不停止，则将一直刷新
            Log.d("刷新","show")
            adapter.refresh()
        }



        databind.refresh1.setRefreshHeader(ClassicsHeader(context))
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { state ->
//                判断状态是否需要进行下一次加载或结束,数据自动完成
//                databind.refresh1.isRefreshing = state.refresh is LoadState.Loading
//                smartrefreshlayout
                databind.refresh1.finishRefresh(state.refresh is LoadState.Loading)
            }
        }
        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.frameLayout, circleFragment.newInstance())
            .commit()

        return databind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance() = newsFragment().apply {
        }
    }

}