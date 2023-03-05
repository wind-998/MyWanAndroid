package com.example.newsfragment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfragment.Entity.NewsEntity
import com.example.newsfragment.databinding.NewsitemBinding

//recycler的item进行绑定
class Newsadapter(private var context: Context): PagingDataAdapter<NewsEntity, Newsadapter.ViewHolder>(
    object : DiffUtil.ItemCallback<NewsEntity>(){
        //避免相同的item进行崇重新绘制，需要一次进行自定义比较
        //都返回true，则不需要进行重新绘制
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            //判断是否id相同
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            //是否内容相同
            return oldItem == newItem
        }


    }
){
    lateinit var call:callback
    fun setcall(listen:callback){
        call = listen
    }
    inner class ViewHolder(var bind: NewsitemBinding): RecyclerView.ViewHolder(bind.root){
        fun binddd(news: NewsEntity?){
            //绑定数据
            bind.textView.text = news?.niceDate.toString()
            bind.textView2.text = news?.title
            bind.textView3.text = news?.shareUser.toString()
            bind.root.setOnClickListener{
                call.ck(news?.link!!)
//                context.startActivity(Intent(context,webViewActivity::class))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = getItem(position)
        holder.binddd(news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //recyclerview的item的布局加载
        var binding = NewsitemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }
    interface callback{
        fun ck(url:String)
    }
}