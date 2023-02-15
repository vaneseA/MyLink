package com.example.mylink.application

import android.app.Application
import androidx.room.Room
import com.example.mylink.data.db.SjDatabase
import com.example.mylink.data.model.SjDomain
import kotlinx.coroutines.*

class MyLinkApplication : Application() {
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
                SjDatabase.getDao().insertDomain(SjDomain(name = "빈 도메인", url = ""))
            }
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        //close Database
        //사실 어디서 닫아야 할 지 잘 모르겠다.
        //백그라운드로 갔을 때도 닫아야 할까?
        SjDatabase.closeDatabase()
    }
}