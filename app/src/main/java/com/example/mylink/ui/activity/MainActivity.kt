package com.example.mylink.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mylink.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Default).launch{
            val wait = launch{ delay(1200) }
            wait.join()
            launch(Dispatchers.Main){
                val intent : Intent = Intent(applicationContext,ViewLinkActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}