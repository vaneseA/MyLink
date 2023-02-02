package com.example.mylink.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mylink.data.LinkTagCrossRef
import com.example.mylink.data.SjDomain
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag

@Dao
interface SjDao {
    @Query("SELECT * FROM SjDomain")
    fun getDomains(): List<SjDomain>

    @Query("SELECT * FROM SjLink")
    fun getLinks(): List<SjLink>

    @Query("SELECT * FROM SjTag")
    fun getTags(): List<SjTag>

    @Insert
    fun insertDomain(newDomain: SjDomain)

    @Insert
    fun insertLinkTagCrossRef(newCrossRef: LinkTagCrossRef)

    @Insert
    fun insertLink(newLink: SjLink)

    @Insert
    fun insertTag(newTag: SjTag)

    @Delete
    fun deleteDomain(newDomain: SjDomain)

    @Delete
    fun deleteLink(newLink: SjLink)

    @Delete
    fun deleteTag(newTag: SjTag)

}