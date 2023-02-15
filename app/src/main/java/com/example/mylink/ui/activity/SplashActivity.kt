package com.example.mylink.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylink.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Default).launch {
            val wait = launch { delay(1200) }
            wait.join()
            //wait and start Activity (And finish this activity)
            launch(Dispatchers.Main) {
                startMainActivityAndFinishThis()
            }
        }
    }

    // 메인액티비티를 시작하고, 이 액티비티를 종료한다.
    private fun startMainActivityAndFinishThis() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}