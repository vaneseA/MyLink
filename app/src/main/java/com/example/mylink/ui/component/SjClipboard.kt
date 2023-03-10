package com.example.mylink.ui.component

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat

class SjClipboard private constructor() {
    companion object {

        fun getClipboardManager(context: Context): ClipboardManager {
            return ContextCompat.getSystemService(context, ClipboardManager::class.java)!!
        }

        fun debugClipMimeType(description: ClipDescription) {
            val html = description.hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)
            val intent = description.hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT)
            val plain = description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) //String, url
            val uriList = description.hasMimeType(ClipDescription.MIMETYPE_TEXT_URILIST)
            val unknown = description.hasMimeType(ClipDescription.MIMETYPE_UNKNOWN)
            Log.d(
                "clipType",
                "html $html intent $intent, plain $plain, uriList $uriList, unknown $unknown"
            )
        }
    }

}