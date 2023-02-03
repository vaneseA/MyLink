package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.SjLinkWithTagsAndDomain
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewLinkViewModel : ViewModel() {
    var _linkList = MutableLiveData(ArrayList<SjLinkWithTagsAndDomain>())
    val linkList: LiveData<ArrayList<SjLinkWithTagsAndDomain>> get() = _linkList!!

    fun loadDatas() {
        viewModelScope.launch(Dispatchers.IO){
            val dao = SjDatabase.db.getDao()
            val links : ArrayList<SjLinkWithTagsAndDomain> =  ArrayList(dao.getLinksWithTagsAndDomain())
            _linkList.postValue(links)
        }
    }

}