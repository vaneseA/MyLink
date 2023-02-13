package com.example.mylink.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mylink.data.SjTag
import com.example.mylink.data.repository.SjRepository

class CreateTagViewModel : ViewModel(){
    private val repository = SjRepository()

    fun insertTag(newTag: SjTag) {
        repository.insertTag(newTag)
    }
}