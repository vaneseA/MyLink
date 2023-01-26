package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mylink.R
import com.example.mylink.SjLink
import com.example.mylink.databinding.FragmentEditLinkBinding

class EditLinkFragment : Fragment() {

    companion object{
        fun newInstance(link: SjLink){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEditLinkBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_link,
            container,
            false
        )
        return binding.root
    }


}