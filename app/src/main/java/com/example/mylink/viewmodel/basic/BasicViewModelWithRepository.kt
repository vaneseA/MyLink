package com.example.mylink.viewmodel.basic

import androidx.lifecycle.ViewModel
import com.example.mylink.data.repository.SjRepository

abstract class BasicViewModelWithRepository : ViewModel(){
    protected val repository = SjRepository.getInstance()

}