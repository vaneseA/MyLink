package com.example.mylink.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.example.mylink.R
import com.example.mylink.databinding.ActivityEditLinkBinding
import com.example.mylink.ui.fragment.EditLinkFragment
import com.example.mylink.viewmodel.EditLinkViewModel

class EditLinkActivity : AppCompatActivity() {
    private val viewModel: EditLinkViewModel by viewModels()

    private lateinit var binding : ActivityEditLinkBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //view binding
        binding = ActivityEditLinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add(R.id.activityRoot, EditLinkFragment())
            setReorderingAllowed(true)
            addToBackStack("editLink")
        }
    }

}