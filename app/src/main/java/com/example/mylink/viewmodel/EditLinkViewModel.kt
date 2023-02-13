package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.SjDomain
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditLinkViewModel(val link: SjLink) : ViewModel() {
    //private var _tags = MutableLiveData<List<SjTag>>()
    val tags: LiveData<List<SjTag>> get() = SjDatabase.getDao().getAllTags()
    private var _domains = MutableLiveData<List<SjDomain>>()
    val domains: LiveData<List<SjDomain>> get() = _domains

    fun loadDataFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = SjDatabase.getDao()
            //_tags.postValue(dao.getAllTags())
            //_domains.postValue(dao.getAllDomains())
        }
    }


}