package com.example.mylink.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylink.data.dao.SjDao
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
}



