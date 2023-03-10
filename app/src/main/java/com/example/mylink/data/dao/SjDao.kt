package com.example.mylink.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mylink.data.db.SjDatabase
import com.example.mylink.data.model.*

@Dao
interface SjDao {

/*
    // get All Entities from Database


    @Query("SELECT * FROM SjTag ORDER BY name")
    fun getAllTags(): LiveData<List<SjTag>>

    @Query("SELECT * FROM SjTagGroup ORDER BY gid")
    fun getAllTagGroups(): LiveData<List<SjTagGroup>>


    @Transaction
    @Query("SELECT * FROM SjLink ORDER BY lid DESC")
    fun getAllLinksAndDomainsWithTags()
            : LiveData<List<SjLinksAndDomainsWithTags>>

    @Transaction
    @Query("SELECT * FROM SjLink WHERE lid NOT IN (SELECT ref.lid FROM LinkTagCrossRef as ref WHERE ref.tid NOT IN (SELECT tag.tid FROM SjTag as tag WHERE tag.gid NOT IN (SELECT g.gid FROM SjTagGroup as g WHERE is_private = 1))) ORDER BY lid DESC")
    fun getPublicLinksAndDomainsWithTags()
            : LiveData<List<SjLinksAndDomainsWithTags>>


    @Transaction
    @Query("SELECT * FROM SjTagGroup ORDER BY name")
    fun getAllTagGroupsWithTags()
            : LiveData<List<SjTagGroupWithTags>>

    @Transaction
    @Query("SELECT * FROM SjTagGroup WHERE is_private=0 ORDER BY gid")
    fun getNotPrivateTagGroupsWithTags()
            : LiveData<List<SjTagGroupWithTags>>

*/


}