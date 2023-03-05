package com.example.otherfragment.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import com.example.otherfragment.databinding.FragmentOtherBinding
import com.example.otherfragment.model.UserModel
import com.example.otherfragment.remote.api
import com.example.otherfragment.viewmodel.Factory
import com.example.otherfragment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class otherFragment : Fragment() {
    private lateinit var databind :FragmentOtherBinding
//    private lateinit var viewmodel: UserViewModel
    @Inject
    lateinit var api:api
private  val viewmodel: UserViewModel by lazy {
    vita.with(VitaOwner.Multiple(requireActivity())).getViewModel({
        Factory(api)
    })
}
    private lateinit var id :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewmodel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

//        用于不同viewmodel之间通信
//        viewmodel = vita.with(VitaOwner.Multiple(requireActivity())).getViewModel<UserViewModel>(){ MyViewModelWithFactory(initData) }
        var share = context?.getSharedPreferences("UserInfo", AppCompatActivity.MODE_PRIVATE)
        id = share?.getString("id","").toString()
        viewmodel.otherinfo.value = UserModel(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databind = FragmentOtherBinding.inflate(LayoutInflater.from(context),container,false)
        viewmodel.otherinfo.observe(viewLifecycleOwner){
            if(it.id ==""){
                databind.button8.isVisible = true
                databind.textView44.isVisible = false
            }else{
                databind.button8.isVisible = false
                databind.textView44.isVisible = true
                databind.textView44.text = it.id
            }
        }

        databind.button8.setOnClickListener{
            startActivity(Intent(context,LoginActivity::class.java))
        }
        databind.button13.setOnClickListener{
            Logout()
        }
        databind.viewmodelw = viewmodel
        databind.lifecycleOwner = this
        return databind.root
    }
    fun Logout(){
        databind.button8.isVisible = true
        databind.textView44.isVisible = false
        viewmodel.otherinfo.value = UserModel("","")
        var share = context?.getSharedPreferences("UserInfo", AppCompatActivity.MODE_PRIVATE)
        var sp = share?.edit()
        sp?.clear()
        sp?.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = otherFragment().apply {
        }
    }
}