package com.example.mylink.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup


class SjNetworkRepository private constructor() {

    private val AGENT = "Mozilla"
    private val REFERRER = "http://www.google.com"
    private val TIMEOUT = 10000
    private val DOC_SELECT_QUERY = "meta[property^=og:]"
    private val OPEN_GRAPH_KEY = "content"
    private val PROPERTY = "property"
    private val OG_IMAGE = "og:image"
    private val OG_DESCRIPTION = "og:description"
    private val OG_URL = "og:url"
    private val OG_TITLE = "og:title"
    private val OG_SITE_NAME = "og:site_name"
    private val OG_TYPE = "og:type"

    private val client = OkHttpClient()
    private val _siteTitle = MutableLiveData<String>()
    val siteTitle: LiveData<String> get() = _siteTitle

    companion object {
        //singleton object
        lateinit var networkRepository: SjNetworkRepository

        fun newInstance(): SjNetworkRepository {
            if (!this::networkRepository.isInitialized) {
                networkRepository = SjNetworkRepository()
            }
            return networkRepository
        }

    }

    suspend fun getPreviewOf(url: String): String {
        if (url.isEmpty() || !url.startsWith("http")) return ""
        try {
            val con2 = Jsoup.connect(url).execute()
            val doc = con2.parse()
            val ogTags = doc.select("meta[property^=og:]")
            when {
                ogTags.size > 0 ->
                    ogTags.forEachIndexed { index, _ ->
                        val tag = ogTags[index]
                        if (tag.attr("property") == OG_IMAGE) {
                            return (tag.attr(OPEN_GRAPH_KEY))
                        }
                    }
            }
        } catch (e: java.lang.Exception) {
            Log.e("error", e.localizedMessage)
        }
        return ""
    }

    fun getTitleOf(url: String) {
        if (url.isEmpty() || !url.startsWith("http")) return
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = Request.Builder().url(url).build()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) Log.e("Error", "UnExpected code $response")
                    val html = Jsoup.parse(response.body!!.string())
                    Log.d("loadTitle", html.title())
                    _siteTitle.postValue(html.title())
                }
            } catch (e: Exception) {
                /*
                유저가 입력한 url이 완벽하지 않아서 오류가 날 수도 있다.
                근데 이건 오류가 아니고, 아직 입력중인 것일 수도 있는 심각하지 않은 문제다.
                 */
            }
        }
    }


}