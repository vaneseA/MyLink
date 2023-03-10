package com.example.mylink.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.model.SjTagGroup
import com.example.mylink.data.model.SjTagGroupWithTags


@Dao
interface SjTagDao {
    // select data list
    @Transaction
    @Query("SELECT * FROM SjTagGroup WHERE gid= 1")
    fun getDefaultTagGroupData(): LiveData<SjTagGroupWithTags>

    @Transaction
    @Query("SELECT * FROM SjTagGroup WHERE gid != 1 ORDER BY name")
    suspend fun selectTagGroupsNotDefault()
            : List<SjTagGroupWithTags>

    @Transaction
    @Query("SELECT * FROM SjTagGroup WHERE gid != 1 AND is_private = 0 ORDER BY name")
    suspend fun selectTagGroupsPublicNotDefault(): List<SjTagGroupWithTags>


    // select single
    @Transaction
    @Query("SELECT * FROM SjTagGroup WHERE gid= :gid")
    suspend fun selectTagGroupByGid(gid: Int): SjTagGroupWithTags

    @Query("SELECT * FROM SjTag WHERE tid = :tid")
    suspend fun selectTagByTid(tid: Int): SjTag


    // insert
    @Insert
    suspend fun insertTag(newTag: SjTag): Long

    @Insert
    suspend fun insertTagGroup(newTagGroup: SjTagGroup): Long


    // update
    @Update
    suspend fun updateTag(tag: SjTag)

    @Update
    suspend fun updateTagGroup(tagGroup: SjTagGroup)

    @Query("UPDATE SjTag SET gid = 1 WHERE gid = :gid")
    suspend fun updateTagsMoveToDefaultTagGroupByGid(gid: Int)


    // delete
    @Query("DELETE FROM SjTag WHERE tid = :tid")
    suspend fun deleteTagByTid(tid: Int)

    @Query("DELETE FROM SjTagGroup WHERE gid = :gid")
    suspend fun deleteTagGroupByGid(gid: Int)

    @Query("DELETE FROM LinkTagCrossRef WHERE tid= :tid")
    suspend fun deleteLinkTagCrossRefsByTid(tid: Int)

    @Query("DELETE FROM SearchTagCrossRef WHERE tid= :tid")
    suspend fun deleteSearchTagCrossRefsByTid(tid: Int)


}