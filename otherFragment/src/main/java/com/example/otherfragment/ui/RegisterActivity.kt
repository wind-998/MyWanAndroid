package com.example.otherfragment.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import com.example.otherfragment.databinding.LoginBinding
import com.example.otherfragment.databinding.RegisterBinding
import com.example.otherfragment.model.UserModel
import com.example.otherfragment.remote.api
import com.example.otherfragment.viewmodel.Factory
import com.example.otherfragment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity :AppCompatActivity(){
    private val dataBinding: RegisterBinding by lazy {
        RegisterBinding.inflate(layoutInflater)
    }
//    private lateinit var viewmodel: UserViewModel
@Inject
lateinit var api: api
    private  val viewmodel: UserViewModel by lazy {
        vita.with(VitaOwner.Multiple(this)).getViewModel({
            Factory(api)
        })
    }
//    private  val viewmodel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)
//        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
        dataBinding.mvvviewmodel = viewmodel
//        dataBinding.mvvviewmodel = viewmodel
        dataBinding.lifecycleOwner = this
    Log.d("wwww",viewmodel.UserInfo.value.toString())

    }
    fun register(view:View){
        CoroutineScope(Dispatchers.Main).launch {
            if(viewmodel.checkRegister()){
                Toast.makeText(this@RegisterActivity,"注册成功！", Toast.LENGTH_SHORT).show()
                 finish()
            }else{
                Toast.makeText(this@RegisterActivity,"注册失败！", Toast.LENGTH_SHORT).show()
            }
        }
    }
}