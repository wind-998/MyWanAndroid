package com.example.newsfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsfragment.databinding.NetworkloaditemBinding

class FooterAdapter(
    val adapter: Newsadapter,
    val context: Context
) : LoadStateAdapter<FooterAdapter.ViewHolder>() {
     class ViewHolder (private val binding: NetworkloaditemBinding, val retryCallback: () -> Unit)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindData(data: LoadState){
                binding.apply {
                    // 正在加载，显示进度条
                    progress.isVisible = data is LoadState.Loading
                    // 加载失败，显示并点击重试按钮
                    retryButton.isVisible = data is LoadState.Error
                    retryButton.setOnClickListener { retryCallback() }
                    // 加载失败显示错误原因
                    errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
                    errorMsg.text = (data as? LoadState.Error)?.error?.message
                }
            }

        }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        //水平居中
//        val params = holder.itemView.layoutParams
//        if (params is StaggeredGridLayoutManager.LayoutParams) {
//            params.isFullSpan = true
//        }
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = NetworkloaditemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding) { adapter.retry() }
    }


}