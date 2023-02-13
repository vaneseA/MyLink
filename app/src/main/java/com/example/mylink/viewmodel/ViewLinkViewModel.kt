package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.*
import com.example.mylink.data.*
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewLinkViewModel : ViewModel() {
    private val _linkList = MutableLiveData(ArrayList<SjLinkAndDomain>())
    private val _domainNames = MutableLiveData<MutableList<String>>()
    private val _domains = MutableLiveData<MutableList<SjDomain>>()
    private val _tags = MutableLiveData<List<SjTag>>()

    val tags: LiveData<List<SjTag>> get() = _tags
    val linkList: LiveData<ArrayList<SjLinkAndDomain>> get() = _linkList
    val domainNames: LiveData<MutableList<String>> get() = _domainNames
    val domains: LiveData<MutableList<SjDomain>> get() = _domains

    fun loadDatas() {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = SjDatabase.getDao()
            val links: ArrayList<SjLinkAndDomain> =
                dao.getLinksAndDomain() as ArrayList<SjLinkAndDomain>
            _linkList.postValue(links)
        }
    }

    fun loadTags() {
        viewModelScope.launch(Dispatchers.IO) {
            _tags.postValue(SjDatabase.getDao().getTags())
        }
    }

    fun loadDomainNamesAndUrls() {
        viewModelScope.launch(Dispatchers.IO) {
            val domainList = SjDatabase.getDao().getDomains()

            val nameList: MutableList<String> = mutableListOf()

            for (domain in domainList) {
                nameList.add( domain.name)
            }
            _domains.postValue(domainList.toMutableList())
            _domainNames.postValue(nameList)
        }
    }

    fun getDomainUrlByPosition(position: Int): String {
        return this.domains.value!![position].url
    }

    fun getDomainIdByPosition(position: Int): Int {
        return this.domains.value!![position].did
    }

    fun insertLinks(link: SjLink, tids: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            val lid: Int = SjDatabase.getDao().insertLink(link).toString().toInt()

            for (tid in tids) {
                SjDatabase.getDao().insertLinkTagCrossRef(
                    LinkTagCrossRef(tid = tid, lid = lid)
                )
            }

        }
    }
}