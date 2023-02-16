package com.example.mylink.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mylink.data.model.SjTag
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class TagViewModel : BasicViewModelWithRepository() {
    val tags = repository.tags

    // data binding live data
    val tagName = MutableLiveData<String>()

    // Model to save
    private var targetTag = SjTag(name = "")

    init {
        // handle user change data
        tagName.observeForever {
            targetTag.name = it
            Log.d("TagName change", it)
        }
    }

    // set update tag data
    fun setTag(tid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val tag = async { repository.getTagByTid(tid) }
            setTag(tag.await())
        }
    }

    private fun setTag(tag: SjTag) {
        targetTag = tag
        tagName.postValue(tag.name)
    }


    // save tag
    fun saveTag() {
        if (targetTag.tid == 0) {
            repository.insertTag(targetTag)
        } else {
            repository.updateTag(targetTag)
        }
    }


    // delete tag
    fun deleteTag(tag: SjTag) = repository.deleteTag(tag)

}