package com.example.happyfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.example.happyfragment.databinding.HappyitemlayoutBinding
import com.example.happyfragment.model.happymodel

class happyAdapter:PagingDataAdapter<happymodel,happyAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<happymodel>(){
    override fun areItemsTheSame(oldItem: happymodel, newItem: happymodel): Boolean {
        //判断是否id相同
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: happymodel, newItem: happymodel): Boolean {
        //是否内容相同
        return oldItem == newItem
    }

}) {
    class ViewHolder(var databing:HappyitemlayoutBinding):RecyclerView.ViewHolder(databing.root) {
        fun bind(happymodel: happymodel){
            databing.textView9.text = happymodel.title
            databing.textView10.text = happymodel.shareUser
            databing.textView8.text = happymodel.niceDate
            databing.root.setOnClickListener{
                ARouter.getInstance().build("/ii/ee")
                    .withString("url", happymodel.link)
                    .navigation()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = HappyitemlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }
}