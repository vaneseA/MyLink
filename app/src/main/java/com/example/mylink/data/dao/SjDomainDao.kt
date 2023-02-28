package com.example.mylink.data.dao

import androidx.room.*
import com.example.mylink.data.model.SjDomain
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjLinksAndDomainsWithTags

@Dao
interface SjDomainDao {
    // select
    @Transaction
    @Query("SELECT * FROM SjLink WHERE did = :did")
    suspend fun selectLinksByDid(did: Int): List<SjLinksAndDomainsWithTags>

    @Query("SELECT * FROM SjDomain WHERE did NOT IN (1)")
    suspend fun selectDomainsNotDefault(): List<SjDomain>


    // select single
    @Query("SELECT * FROM SjDomain WHERE did = :did")
    suspend fun selectDomainByDid(did: Int): SjDomain


    // insert
    @Insert
    suspend fun insertDomain(newDomain: SjDomain): Long


    // update
    @Update
    suspend fun updateDomain(domain: SjDomain)

    @Update
    suspend fun updateLinks(vararg link: SjLink)


    //delete
    @Delete
    suspend fun deleteDomain(newDomain: SjDomain)

    @Query("DELETE FROM SjLink WHERE did = :did")
    suspend fun deleteLinksByDid(did: Int)


}