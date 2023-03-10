package com.example.mylink.data.repository.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylink.data.dao.SjTagDao
import com.example.mylink.data.db.SjDatabaseUtil
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.model.SjTagGroup
import com.example.mylink.data.model.SjTagGroupWithTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SjTagRepository private constructor() {
    private val dao: SjTagDao = SjDatabaseUtil.getTagDao()

    private val _tagGroupsWithoutDefault = MutableLiveData<List<SjTagGroupWithTags>>()
    val tagGroupsWithoutDefault: LiveData<List<SjTagGroupWithTags>> get() = _tagGroupsWithoutDefault
    val defaultTagGroup = dao.getDefaultTagGroupData()

    companion object {
        // singleton object
        private lateinit var repo: SjTagRepository

        fun getInstance(): SjTagRepository {
            if (!this::repo.isInitialized) {
                repo = SjTagRepository()
            }
            return repo
        }
    }


    // manage liveData
    fun postTagGroupsNotDefault() =
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.selectTagGroupsNotDefault()
            _tagGroupsWithoutDefault.postValue(data)
        }

    fun postTagGroupsPublicNotDefault() =
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.selectTagGroupsPublicNotDefault()
            _tagGroupsWithoutDefault.postValue(data)
        }


    // select
    suspend fun selectTagByTid(tid: Int): SjTag = dao.selectTagByTid(tid)

    suspend fun selectTagGroupByGid(gid: Int): SjTagGroupWithTags =
        dao.selectTagGroupByGid(gid)


    // insert
    suspend fun insertTagGroup(name: String, isPrivate:Boolean) {
        dao.insertTagGroup(SjTagGroup(name = name, isPrivate = isPrivate))
    }

    private fun insertTag(newTag: SjTag) =
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertTag(newTag)
        }

    fun insertTag(name: String, gid: Int = 1) = insertTag(SjTag(name = name, gid = gid))

    //  update
    suspend fun updateTag(tag: SjTag) {
        dao.updateTag(tag)
    }

    suspend fun updateTagGroup(tagGroup: SjTagGroup) {
        dao.updateTagGroup(tagGroup)
    }


    // delete
    fun deleteTagGroupByGid(gid: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            // change tags related to group to basic group
            val job = launch { dao.updateTagsMoveToDefaultTagGroupByGid(gid) }

            job.join()
            // delete group
            dao.deleteTagGroupByGid(gid)
        }
    }

    suspend fun deleteLinkTagCrossRefsByTid(tid: Int) {
        dao.deleteLinkTagCrossRefsByTid(tid)
    }

    suspend fun deleteSearchTagCrossRefsByTid(tid: Int) {
        dao.deleteSearchTagCrossRefsByTid(tid)
    }

    suspend fun deleteTag(tid: Int) {
        // wait and delete
        dao.deleteTagByTid(tid)
    }


}