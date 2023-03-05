package com.example.circleview.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.circleview.ui.circleFragment

@BindingAdapter("image")
fun setImage(imageView: ImageView,string: String){
    Glide.with(imageView.context)
        .load(string)
        .into(imageView);

}