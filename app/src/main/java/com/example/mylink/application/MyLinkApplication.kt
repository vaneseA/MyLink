package com.example.mylink.application

import android.app.Application
import com.example.mylink.data.db.SjDatabaseUtil
import com.example.mylink.data.model.SjDomain
import com.example.mylink.data.model.SjTagGroup
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import kotlinx.coroutines.*

class MyLinkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //open Database
        SjDatabaseUtil.openDatabase(applicationContext)

        //insert initial Data
        CoroutineScope(Dispatchers.IO).launch {

            val countDomain = async {
                SjDatabaseUtil.getDao().getDomainCount()
            }
            if (countDomain.await() == 0) {
                SjDatabaseUtil.getDao().insertDomain(SjDomain(did = 1, name = "-", url = ""))
            }

            val countTagGroup = async {
                SjDatabaseUtil.getDao().getTagGroupCount()
            }
            if (countTagGroup.await() == 0) {
                SjDatabaseUtil.getDao().insertTagGroup(SjTagGroup(gid = 1, name = "-", isPrivate = false))
            }
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        //close Database
        //사실 어디서 닫아야 할 지 잘 모르겠다.
        //백그라운드로 갔을 때도 닫아야 할까?
        SjDatabaseUtil.closeDatabase()
    }


}