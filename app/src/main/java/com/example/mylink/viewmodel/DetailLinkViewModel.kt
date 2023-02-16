package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.repository.SjNetworkRepository
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailLinkViewModel : BasicViewModelWithRepository() {
    private val networkRepository = SjNetworkRepository.newInstance()

    private val _link = MutableLiveData<SjLinksAndDomainsWithTags>()
    val link: LiveData<SjLinksAndDomainsWithTags> get() = _link
    private val _tags = MutableLiveData<List<SjTag>>()
    val tags: LiveData<List<SjTag>> get() = _tags
    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl


    fun loadLinkData(lid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getLinkAndDomainWithTagsByLid(lid)
            _link.postValue(data)
            _tags.postValue(data.tags)
        }
    }

    fun loadPreviewImageUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val url = async { networkRepository.getPreviewOf(url) }
            _imageUrl.postValue(url.await())
        }

    }

    fun deleteLink(link: SjLink, tags: List<SjTag>) = repository.deleteLink(link, tags)


}