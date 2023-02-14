package com.example.mylink.viewmodel

import com.example.mylink.data.model.SjTag
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository

class CreateTagViewModel : BasicViewModelWithRepository(){

    fun insertTag(newTag: SjTag) {
        repository.insertTag(newTag)
    }

}