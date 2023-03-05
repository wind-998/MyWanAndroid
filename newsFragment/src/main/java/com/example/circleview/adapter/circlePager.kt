package com.example.circleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.circleview.entity.info
import com.example.newsfragment.databinding.CircleitemBinding

class circlePager(private  var imagePath:List<info>): RecyclerView.Adapter<circlePager.viewHolder>() {
    inner class viewHolder(var databing:CircleitemBinding):RecyclerView.ViewHolder(databing.root) {
        fun bind(position: Int){
            databing.image = imagePath.get(position).imagePath
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var databind = CircleitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(databind)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return imagePath.size
    }
//    override fun getCount(): Int {
//        return imagePath.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        var databind = CircleitemBinding.inflate(LayoutInflater.from(container.context))
//        databind.image = imagePath.get(position).imagePath
//        container.addView(databind.root)
//        return databind.root
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
}