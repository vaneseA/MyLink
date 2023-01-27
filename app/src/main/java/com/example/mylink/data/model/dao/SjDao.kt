package com.example.mylink.data.model.dao

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