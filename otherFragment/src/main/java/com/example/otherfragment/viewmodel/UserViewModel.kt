package com.example.otherfragment.viewmodel

import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otherfragment.model.UserModel
import com.example.otherfragment.model.UsetModelRefister
import com.example.otherfragment.remote.api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
open class UserViewModel  constructor(private val api: api):ViewModel() {
        var otherinfo:MutableLiveData<UserModel> = MutableLiveData(UserModel("",""))

        var UserInfo:MutableLiveData<UserModel> = MutableLiveData(UserModel("",""))
        suspend fun check():Boolean{
                var result = api.getCheck(UserInfo.value?.id, UserInfo.value?.password)
                Log.d("登录数据",result.toString())
                if(result.data == null){
                        return false
                }else{
//                        otherinfo.value?.id = UserInfo.value?.id
//                        otherinfo.value?.password= UserInfo.value?.password
                        //改变了对象，导致无法更新
                       otherinfo.value = UserModel(UserInfo.value?.id,UserInfo.value?.password)
                        Log.d("viewmodel",otherinfo.value.toString())
                        Log.d("viewmodel",UserInfo.value.toString())
                        Log.d("viewmodel",UserInfoRegister.value.toString())
                        return true
                }

        }

        var UserInfoRegister:MutableLiveData<UsetModelRefister> = MutableLiveData(UsetModelRefister("","",""))
        suspend fun checkRegister():Boolean{
                var result = api.getregister(UserInfoRegister.value?.id, UserInfoRegister.value?.password,UserInfoRegister.value?.repassword)
                Log.d("注册数据",result.toString())
                if(result.data == null){
                        return false
                }else{
                        UserInfo.value = UserModel(UserInfoRegister.value?.id,UserInfoRegister.value?.password)
                        Log.d("viewmodel",otherinfo.value.toString())
                        Log.d("viewmodel",UserInfo.value.toString())
                        Log.d("viewmodel",UserInfoRegister.value.toString())
                        return true
                }
        }


}