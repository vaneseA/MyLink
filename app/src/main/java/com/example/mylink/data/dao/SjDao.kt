package com.example.mylink.data.dao

import androidx.room.*
import com.example.mylink.data.*


@Dao
interface SjDao {
    @Query("SELECT * FROM SjDomain")
    fun getDomains(): List<SjDomain>

    @Query("SELECT * FROM SjLink")
    fun getLinks(): List<SjLink>

    @Query("SELECT * FROM SjTag")
    fun getTags(): List<SjTag>

    @Transaction
    @Query("SELECT * FROM SjLink")
    fun getLinksWithTags(): List<SjLinkWithTags>

    @Transaction
    @Query("SELECT * FROM SjLink")
    fun getLinksAndDomain(): List<SjLinkAndDomain>

//    @Transaction
//    @Query("SELECT * FROM SjLink")
//    fun getLinksWithTagsAndDomain():List<SjLinkWithTagsAndDomain>

    @Insert
    fun insertDomain(newDomain: SjDomain)

    @Insert
    fun insertLinkTagCrossRef(newCrossRef: LinkTagCrossRef)

    @Insert
    fun insertLink(newLink:SjLink)

    @Insert
    fun insertTag(newTag:SjTag)

    @Delete
    fun deleteDomain(newDomain: SjDomain)

    @Delete
    fun deleteLink(newLink:SjLink)

    @Delete
    fun deleteTag(newTag:SjTag)

}