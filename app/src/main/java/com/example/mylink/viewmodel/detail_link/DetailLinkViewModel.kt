package com.example.mylink.viewmodel.detail_link


import androidx.lifecycle.*
import com.example.mylink.data.model.*
import com.example.mylink.data.repository.room.SjLinkRepository
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository
import com.example.mylink.viewmodel.basic.SjBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailLinkViewModel : SjBaseViewModel() {
    private val linkRepo = SjLinkRepository.getInstance()

    var lid: Int? = null
        set(data) {
            field = data
            refreshData()
        }

    private var _link = MutableLiveData<LinkDetailValue>()
    val link: LiveData<LinkDetailValue> get() = _link

    // binding variables
    val bindingLinkName: LiveData<String> = Transformations.map(link) { it.name }
    val bindingTags: LiveData<List<SjTag>> = Transformations.map(link) { it.tags }
    val bindingFullUrl: LiveData<String> = Transformations.map(link) { it.fullUrl }


    override fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = linkRepo.selectLinkValueByLid(lid!!)
            _link.postValue(data)
        }
    }

    fun deleteLink() {
        linkRepo.deleteLinkByLid(lid!!)
    }


}