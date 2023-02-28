package com.example.mylink.viewmodel.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.model.SjDomain
import com.example.mylink.data.repository.room.SjDomainRepository
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository
import com.example.mylink.viewmodel.basic.SjBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DomainViewModel : SjBaseViewModel() {
    private val domainRepo = SjDomainRepository.getInstance()

    var did = -1
        set(value) {
            field = value
            refreshData()
        }

    // Data binding live data
    val bindingDomainName = MutableLiveData<String>()
    val bindingDomainUrl = MutableLiveData<String>()

    // Model to save
    private var targetDomain = SjDomain(name = "", url = "")


    init {
        // handle user change data
        bindingDomainName.observeForever {
            targetDomain.name = it
        }
        bindingDomainUrl.observeForever {
            targetDomain.url = it
        }
    }

    override fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            val domain = async { domainRepo.selectDomainByDid(did) }
            setDomain(domain.await())
        }
    }

    private fun setDomain(domain: SjDomain) {
        targetDomain = domain
        bindingDomainName.postValue(domain.name)
        bindingDomainUrl.postValue(domain.url)
    }


    // save domain
    fun saveDomain() =
        viewModelScope.launch(Dispatchers.IO) {
            if (targetDomain.did == 0) {
                domainRepo.insertDomain(targetDomain)
            } else {
                domainRepo.updateDomain(targetDomain)
            }
        }


    //delete domain
    fun deleteDomain(domain: SjDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            domainRepo.deleteDomain(domain)
        }


}