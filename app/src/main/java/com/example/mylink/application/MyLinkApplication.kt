package com.example.mylink.application

import android.app.Application
import androidx.room.Room
import com.example.mylink.data.db.SjDatabase
import com.example.mylink.data.model.SjDomain
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import kotlinx.coroutines.*

class MyLinkApplication : Application() {


    private val cacheSize: Long = 90 * 1024 * 1024
    private lateinit var cacheEvictor: LeastRecentlyUsedCacheEvictor
    private lateinit var exoplayerDatabaseProvider: ExoDatabaseProvider

    companion object{
        lateinit var cache: SimpleCache
    }

    override fun onCreate() {
        super.onCreate()

        //open Database
        SjDatabase.openDatabase(applicationContext)

        //insert initial Data
        CoroutineScope(Dispatchers.IO).launch {

            val count = async {
                SjDatabase.getDao().getDomainCount()
            }
            if (count.await() == 0) {
                SjDatabase.getDao().insertDomain(SjDomain(did = 1, name = "-", url = ""))
            }
        }

    //cache of video
    cacheEvictor = LeastRecentlyUsedCacheEvictor(cacheSize)
    exoplayerDatabaseProvider = ExoDatabaseProvider(this)
    cache = SimpleCache(cacheDir, cacheEvictor, exoplayerDatabaseProvider)
}
    override fun onTerminate() {
        super.onTerminate()

        //close Database
        //사실 어디서 닫아야 할 지 잘 모르겠다.
        //백그라운드로 갔을 때도 닫아야 할까?
        SjDatabase.closeDatabase()
    }
}