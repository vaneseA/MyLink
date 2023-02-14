package com.example.mylink.viewmodel

import android.util.Log
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjTag
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository

class ReadLinkViewModel : BasicViewModelWithRepository() {
    val linkList get() = repository.linkList
    val searchLinkList get() = repository.searchLinkList
    var mode: ListMode = ListMode.MODE_ALL

    fun deleteLink(link: SjLink, tags: List<SjTag>) {
        repository.deleteLink(link, tags)
    }

    fun searchLinkByLinkName(linkName: String) {
        this.mode = ListMode.MODE_SEARCH
        Log.i("mode changed",mode.name)
        repository.searchLinksByLinkName(linkName)
    }
}

enum class ListMode {
    MODE_ALL, MODE_SEARCH;
}