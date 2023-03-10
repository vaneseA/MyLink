package com.example.mylink.viewmodel.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.repository.room.SjLinkRepository
import com.example.mylink.data.repository.room.SjSearchSetRepository
import com.example.mylink.data.repository.room.SjTagRepository
import com.example.mylink.viewmodel.basic.SjBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// enum to save mode status
enum class ListMode {
    MODE_ALL, MODE_SEARCH;
}

class SearchLinkViewModel : SjBaseViewModel() {
    private val searchSetRepo = SjSearchSetRepository.getInstance()
    private val linkRepo = SjLinkRepository.getInstance()
    private val tagRepo = SjTagRepository.getInstance()

    // mode
    private lateinit var mode: ListMode

    // binding
    val bindingSearchWord = MutableLiveData<String>()
    private val _bindingSearchTags = MutableLiveData<List<SjTag>>()
    val bindingSearchTags: LiveData<List<SjTag>> get() = _bindingSearchTags

    private lateinit var _searchTagMap: MutableMap<Int, SjTag>
    private val _tids: List<Int> get() = _searchTagMap.keys.toList()


    // data
    val links = linkRepo.links
    val bindingSearchSets = searchSetRepo.searchSetList

    val defaultTags = tagRepo.defaultTagGroup
    val tagGroups = tagRepo.tagGroupsWithoutDefault

    init {
        initValuesAndSetModeAll()
    }

    fun initValuesAndSetModeAll() {
        this.mode = ListMode.MODE_ALL
        this.bindingSearchWord.postValue("")
        _searchTagMap = mutableMapOf()
        updateTags()
    }

    override fun refreshData() {
        refreshLinks()
        refreshSearchSets()
        refreshTags()
    }

    private fun refreshTags() {
        when (isPrivateMode) {
            true -> {
                tagRepo.postTagGroupsPublicNotDefault()
            }
            false -> {
                tagRepo.postTagGroupsNotDefault()
            }
        }
    }

    private fun refreshSearchSets() {
        when (isPrivateMode) {
            true -> searchSetRepo.postSearchSetPublic()
            false -> searchSetRepo.postAllSearchSet()
        }
    }

    private fun refreshLinks() {
        when (mode) {
            ListMode.MODE_ALL -> {
                when (isPrivateMode) {
                    true -> linkRepo.postLinksPublic()
                    false -> linkRepo.postAllLinks()
                }
            }
            ListMode.MODE_SEARCH -> {
                when (isPrivateMode) {
                    true -> linkRepo.postLinksPublicByKeywordAndTids(
                        bindingSearchWord.value!!,
                        _tids
                    )
                    false -> linkRepo.postLinksByKeywordAndTids(bindingSearchWord.value!!, _tids)
                }
            }
        }
    }

    // set search data
    fun setSearch(keyword: String, tags: List<SjTag>) =
        viewModelScope.launch(Dispatchers.Main) {
            val keywordJob = launch{bindingSearchWord.postValue(keyword)}
            val tagsJob = launch {
                _searchTagMap.clear()
                for (tag in tags) _searchTagMap[tag.tid] = tag
            }
            keywordJob.join()
            tagsJob.join()
            updateTags()
        }


    // search methods
    fun isSearchSetEmpty(): Boolean =
        bindingSearchWord.value!!.isEmpty() && _searchTagMap.isEmpty()

    fun startSearchAndSaveIfNotEmpty() {
        updateTags()
        if (isSearchSetEmpty()) {
            initValuesAndSetModeAll()
        } else {
            Log.d("?????? ??????", "Search")
            this.mode = ListMode.MODE_SEARCH
            refreshLinks()
            searchSetRepo.insertSearchSet(bindingSearchWord.value!!, _tids)
        }
    }

    fun clearSearchSet() {
        initValuesAndSetModeAll()
        refreshLinks()
    }


    // delete searchSets
    fun deleteAllSearchSet() =
        viewModelScope.launch {
            val deleteJob = searchSetRepo.deleteAllSearchSet()
            launch(Dispatchers.Main) {
                deleteJob.join()
                refreshSearchSets()
            }
        }


    // manage tag selection
    fun addTag(tag: SjTag) {
        _searchTagMap[tag.tid] = tag
    }

    private fun updateTags() {
        _bindingSearchTags.postValue(_searchTagMap.values.toList())
    }

    fun removeTag(tag: SjTag) {
        _searchTagMap.remove(tag.tid)
    }

    fun containsTag(tag: SjTag) = _searchTagMap.containsKey(tag.tid)


}

