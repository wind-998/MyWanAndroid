package com.example.searchactivity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.example.searchactivity.databinding.ItemBinding
import com.example.searchactivity.model.SearchModel

class pagingadapter: PagingDataAdapter<SearchModel, pagingadapter.ViewHolder>(object :
    DiffUtil.ItemCallback<SearchModel>() {
    override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
        return oldItem == newItem
    }

}) {
    class ViewHolder(var databing:ItemBinding):RecyclerView.ViewHolder(databing.root) {
        fun binddd(content: SearchModel?){
            //绑定数据
            databing.textView24.text = content?.niceDate.toString()
            databing.textView25.text = content?.title
            databing.textView26.text = content?.shareUser.toString()
            databing.root.setOnClickListener{
                ARouter.getInstance().build("/ii/ee")
                    .withString("url", content?.link)
                    .navigation()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = getItem(position)
        holder.binddd(content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
}