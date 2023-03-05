package com.example.otherfragment.ui

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import com.example.otherfragment.databinding.LoginBinding
import com.example.otherfragment.model.UserModel
import com.example.otherfragment.remote.api
import com.example.otherfragment.viewmodel.Factory
import com.example.otherfragment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity:AppCompatActivity() {
    private val dataBinding:LoginBinding by lazy {
        LoginBinding.inflate(layoutInflater)
    }
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
//        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
//        viewmodel = vita.with(VitaOwner.Multiple(this)).getViewModel<UserViewModel>()
        dataBinding.myviewmodel = viewmodel
        dataBinding.lifecycleOwner = this
        setContentView(dataBinding.root)
        Thread(){
            while (true){
                sleep(5000)
                Log.d("Userinfo",viewmodel.UserInfo.value.toString())
            }
        }.start()

//        dataBinding.button33.setOnClickListener{
////            val newUserInfo = viewmodel.UserInfo.value?.copy(id = "danwdkjanwdkanwdkjanwdk")
////            viewmodel.UserInfo.postValue(newUserInfo)
//            viewmodel.UserInfo.value = UserModel("danwdkjanwdkanwdkjanwdk", "new_password")//不可在viewmodel内修改
//        }
    }
    fun goregister(view: View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }
    fun login(view: View){
        CoroutineScope(Dispatchers.Main).launch {
            if(viewmodel.check()){
                var share = getSharedPreferences("UserInfo", MODE_PRIVATE)
                var sp = share.edit()
                sp.putString("id",viewmodel.UserInfo.value?.id)
                sp.putString("password",viewmodel.UserInfo.value?.password)
                sp.apply()
//                导航到主界面，使用启动模式，确保只有一个实例活动
                ARouter.getInstance().build("/main/activi")
                    .navigation()
                Toast.makeText(this@LoginActivity,"登录成功！",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this@LoginActivity,"账号或密码不匹配！",Toast.LENGTH_SHORT).show()
            }
        }
    }
}