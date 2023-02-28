package com.example.mylink.viewmodel.tag

import androidx.lifecycle.LiveData
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.model.SjTagGroup
import com.example.mylink.data.model.SjTagGroupWithTags
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository

class ListGroupViewModel : BasicViewModelWithRepository() {
    val tagGroups = repository.tagGroups
    val publicTagGroups = repository.publicTagGroups

    // data binding live data
    private val _bindingBasicTagGroup = repository.defaultTagGroup
    val bindingBasicTagGroup: LiveData<SjTagGroupWithTags> get() = _bindingBasicTagGroup

    fun editTagGroup(name: String, isPrivate: Boolean, group: SjTagGroup?) {
        repository.editTagGroup(name, isPrivate, group)
    }

    fun deleteTagGroup(gid:Int){
        repository.deleteTagGroup(gid)
    }


}