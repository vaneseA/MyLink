package com.example.mylink.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylink.data.dao.*
import com.example.mylink.data.model.*

@Database(
    version = 3,
    entities = [
        SjTag::class,
        SjLink::class,
        SjDomain::class,
        LinkTagCrossRef::class,
        SjSearch::class,
        SearchTagCrossRef::class,
        SjTagGroup::class
    ]
)
abstract class SjDatabase : RoomDatabase() {
    abstract fun getDao(): SjDao
    abstract fun getLinkDao(): SjLinkDao
    abstract fun getCountDao(): SjCountDao
    abstract fun getTagDao(): SjTagDao
    abstract fun getSearchSetDao(): SjSearchSetDao
    abstract fun getDomainDao(): SjDomainDao
}



