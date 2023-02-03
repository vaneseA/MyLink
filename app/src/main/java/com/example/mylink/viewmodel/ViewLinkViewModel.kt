package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.SjLink
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewLinkViewModel : ViewModel() {
    var _linkList = MutableLiveData(ArrayList<SjLink>())
    val linkList: LiveData<ArrayList<SjLink>> get() = _linkList!!

    fun loadDatas() {
        viewModelScope.launch(Dispatchers.IO){
            val dao = SjDatabase.db.getDao()
            val links : ArrayList<SjLink> =  ArrayList(dao.getLinks())
            _linkList.postValue(links)
        }
    }
}