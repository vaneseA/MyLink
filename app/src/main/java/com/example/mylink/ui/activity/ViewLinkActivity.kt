package com.example.mylink.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylink.*
import com.example.mylink.ui.adapter.LinksAdapter
import com.example.mylink.viewmodel.ViewLinkViewModel

class ViewLinkActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.ViewLinkActivity_recyclerView) }
    private val viewModel: ViewLinkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_link)

        val adapter = LinksAdapter(::startWebBrowser)
        viewModel.linkList.observe(this,
            Observer {
                adapter.itemList = it
                adapter.notifyDataSetChanged()
            }
        )
        viewModel.loadDatas()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun startWebBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}