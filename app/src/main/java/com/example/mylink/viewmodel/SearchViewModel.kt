package com.example.mylink.viewmodel

import com.example.mylink.data.model.SjSearch
import com.example.mylink.data.model.SjTag
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository


class SearchViewModel : BasicViewModelWithRepository() {
    val tagList = repository.tags
    val selectedTags=mutableListOf<SjTag>()
    val searchList = repository.searches


    fun saveSearch(newSearch: SjSearch){
        repository.saveSearchAndTags(newSearch,selectedTags)
    }
    fun deleteAllSearch(){
        repository.deleteSearch()
    }

}