package com.example.mylink.viewmodel.edit_link

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.model.*
import com.example.mylink.data.repository.SjNetworkRepository
import com.example.mylink.data.repository.room.SjLinkRepository
import com.example.mylink.data.repository.room.SjTagRepository
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository
import com.example.mylink.viewmodel.basic.SjBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EditLinkViewModel : SjBaseViewModel() {
    // repo
    private val tagRepo = SjTagRepository.getInstance()
    private val linkRepo = SjLinkRepository.getInstance()
    private val networkRepository = SjNetworkRepository.newInstance()

    var lid: Int? = null
        set(value) {
            field = value
            refreshData()
        }

    // model list
    val tagGroups = tagRepo.tagGroupsWithoutDefault
    val tagDefaultGroup = tagRepo.defaultTagGroup

    // default type
    private val defaultType = ELinkType.link

    // Model to Save
    private var targetLink = SjLink(lid = 0, did = -1, name = "", url = "", type = defaultType)
    private var targetDomain = SjDomain(did = 1, name = "", url = "")
    private val targetTags = mutableListOf<SjTag>()

    // binding live data
    private val _previewImage = MutableLiveData<String>()
    private val _bindingUrl = MutableLiveData("")
    val bindingPreviewImage: LiveData<String> get() = _previewImage
    val bindingUrl: LiveData<String> get() = _bindingUrl
    val bindingName = MutableLiveData<String>()
    val bindingIsVideo = MutableLiveData(defaultType == ELinkType.video)


    init {
        bindingName.observeForever {
            targetLink.name = it
        }

        bindingIsVideo.observeForever {
            targetLink.type =
                when (it) {
                    true -> ELinkType.video
                    false -> ELinkType.link
                }
        }
    }

    override fun refreshData() {
        if (isPrivateMode) {
            tagRepo.postTagGroupsPublicNotDefault()
        } else {
            tagRepo.postTagGroupsNotDefault()
        }
    }

    // when create new with url address
    fun createLinkByUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            targetLink.url = url
            _bindingUrl.postValue(url)

            // load title of url site
            launch {
                val title = networkRepository.getTitleOf(url)
                bindingName.postValue(title)
            }

            // load preview of url site
            launch {
                val preview = networkRepository.getPreviewOf(url)
                targetLink.preview = preview
                _previewImage.postValue(preview)
            }
        }
    }

    // when updating existing SjLink
    fun setLinkByLid(lid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val link = async { linkRepo.selectLinkByLid(lid) }
            setData(link.await())
        }
    }

    private fun setData(link: SjLinksAndDomainsWithTags) {
        setLink(link.link)
        setDomain(link.domain)
        setTags(link.tags)
    }

    private fun setTags(tags: List<SjTag>) {
        targetTags.clear()
        targetTags.addAll(tags)
    }

    private fun setDomain(domain: SjDomain) {
        targetDomain = domain
    }

    private fun setLink(link: SjLink) {
        targetLink = link
        _bindingUrl.postValue(link.url)
        bindingName.postValue(link.name)
        _previewImage.postValue(link.preview)
        bindingIsVideo.postValue(link.type == ELinkType.video)
    }

    fun createTag(name: String) {
        tagRepo.insertTag(name)
    }


    // handle tag selection
    fun selectTag(tag: SjTag)=targetTags.add(tag)
    fun unselectTag(tag: SjTag) =targetTags.remove(tag)
    fun isTagSelected(tag: SjTag) = targetTags.contains(tag)


    // save link
    fun saveVideo() {
        viewModelScope.launch {
            if (targetLink.lid != 0) {
                linkRepo.updateLinkAndTags(targetDomain, targetLink, targetTags)
            } else {
                linkRepo.insertLinkAndTags(targetDomain, targetLink, targetTags)
            }
        }

    }


}