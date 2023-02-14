package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import com.example.mylink.data.model.SjDomain
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjTag
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository

class CreateLinkViewModel : BasicViewModelWithRepository(){
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

    fun getSelectedDomainName(): String {
        return if (this::selectedDomain.isInitialized) {
            selectedDomain.url
        }else{
            ""
        }
    }

}