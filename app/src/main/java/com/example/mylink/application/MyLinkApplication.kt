package com.example.mylink.application

import android.app.Application
import androidx.room.Room
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyLinkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //open Database
        SjDatabase.openDatabase(applicationContext)

        //insert initial Data
        GlobalScope.launch(Dispatchers.IO) {
            //SjDatabase.insertFirstData()
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