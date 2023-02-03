package com.example.mylink.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylink.data.LinkTagCrossRef
import com.example.mylink.data.SjDomain
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag
import com.example.mylink.data.dao.SjDao

@Database(entities = [SjTag::class, SjLink::class, SjDomain::class, LinkTagCrossRef::class], version = 1)
abstract class SjDatabase : RoomDatabase() {
    companion object {
        lateinit var db: SjDatabase

        fun insertFirstData() {
            val dao = db.getDao()

            var youtube = SjDomain(0,"유튜브", "https://m.youtube.com/watch?")
            var allchan = SjDomain(1, "올찬식탁", "https://m.smartstore.naver.com/allchanfood/products/")
            dao.insertDomain(youtube)
            dao.insertDomain(allchan)

            var bread = SjTag(0,"빵")
            dao.insertTag(bread)


            var pokemonBread = SjLink(0,"돌아온 포켓몬빵", allchan.did, "6333541088")
            var pieceCake = SjLink(1,"조각 케이크", allchan.did, "718907248")
            var ytnNews = SjLink(2,"포켓몬빵 뉴스", youtube.did, "v=GJ3a33gdx7o")
            dao.insertLinkTagCrossRef(LinkTagCrossRef(pokemonBread.lid,bread.tid))
            dao.insertLinkTagCrossRef(LinkTagCrossRef(pieceCake.lid,bread.tid))
            dao.insertLinkTagCrossRef(LinkTagCrossRef(ytnNews.lid,bread.tid))
            dao.insertLink(pokemonBread)
            dao.insertLink(pieceCake)
            dao.insertLink(ytnNews)
        }
    }

    abstract fun getDao(): SjDao
}