package com.example.mylink.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mylink.data.SjDomain
import com.example.mylink.data.repository.SjRepository

class CreateDomainViewModel :ViewModel(){
    private val repository = SjRepository()

    fun insertDomain(newDomain: SjDomain) {
        repository.insertDomain(newDomain)
    }
}