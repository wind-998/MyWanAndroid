package com.example.happyfragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.happyfragment.databinding.TabitemlayoutBinding
import com.example.happyfragment.hviewmodel.HappyViewModel
import com.example.happyfragment.model.tabmodel

class tabadapter(var tabdata: List<tabmodel>,var viewModel: HappyViewModel) :RecyclerView.Adapter<tabadapter.ViewHolder>() {
    inner class ViewHolder(var databing:TabitemlayoutBinding):RecyclerView.ViewHolder(databing.root){
        fun bind(position: Int){
            databing.textView.text = tabdata.get(position).name
            databing.root.setOnClickListener{
                viewModel.flag.value = tabdata.get(position).id
                Log.d("tabId",viewModel.flag.value.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var databind = TabitemlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(databind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        getItemId(position)
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return tabdata.size
    }
}