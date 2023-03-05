package com.example.otherfragment.adapter

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("setname")
fun setName(editText: EditText,string: String){
        editText.setText(string)
}
@BindingAdapter("setpassword")
fun setPassword(editText: EditText,string: String){
        editText.setText(string)
}