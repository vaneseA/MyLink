package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mylink.data.SjDomain
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag
import com.example.mylink.data.repository.SjRepository

class CreateLinkViewModel : ViewModel() {
    private val repository = SjRepository()
    val domains: LiveData<List<SjDomain>> get() = repository.domains
    val tags: LiveData<List<SjTag>> get() = repository.tags
    val domainNames get() = repository.domainNames

    val selectedTags= mutableListOf<SjTag>()
    lateinit var selectedDomain: SjDomain

    fun selectDomain(position: Int) {
        selectedDomain = domains.value!![position]
    }

    fun insertLink(link:SjLink){
        repository.insertLink(selectedDomain,link,selectedTags)
    }

}