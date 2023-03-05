package com.example.studyfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyfragment.R
import com.example.studyfragment.databinding.FragmentStudyBinding


class studyFragment : Fragment() {
    private lateinit var databind :FragmentStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databind = FragmentStudyBinding.inflate(LayoutInflater.from(context),container,false)
        return databind.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = studyFragment().apply {
        }
    }
}