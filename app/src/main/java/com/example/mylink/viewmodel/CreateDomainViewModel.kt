package com.example.mylink.viewmodel

import com.example.mylink.data.model.SjDomain
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository

class CreateDomainViewModel : BasicViewModelWithRepository() {

    fun insertDomain(newDomain: SjDomain) {
        repository.insertDomain(newDomain)
    }

}